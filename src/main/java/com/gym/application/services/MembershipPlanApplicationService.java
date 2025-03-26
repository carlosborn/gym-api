package com.gym.application.services;

import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.MembershipPlanStatus;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.exceptions.MembershipPlanNotFoundException;
import com.gym.domain.services.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MembershipPlanApplicationService {

    @Autowired
    private MembershipPlanService membershipPlanService;

    public MembershipPlanEntity createMembershipPlan(String name, Double monthlyFee, Integer duration, MembershipPlanStatus status) {
        MembershipPlanEntity membershipPlanEntity = new MembershipPlanEntity();
        membershipPlanEntity.setName(name);
        membershipPlanEntity.setMonthlyFee(monthlyFee);
        membershipPlanEntity.setDuration(duration);
        membershipPlanEntity.setStatus(status);
        membershipPlanEntity.setCreatedAt(new Date());
        membershipPlanEntity.setUpdatedAt(new Date());

        return this.membershipPlanService.save(membershipPlanEntity);
    }

    public MembershipPlanEntity updateMembershipPlan(Long id, String name, Double monthlyFee, Integer duration, MembershipPlanStatus status, Date finishedAt) {
        MembershipPlanEntity membershipPlanEntity = this.getById(id);
        membershipPlanEntity.setName(name);
        membershipPlanEntity.setMonthlyFee(monthlyFee);
        membershipPlanEntity.setDuration(duration);
        membershipPlanEntity.setStatus(status);
        membershipPlanEntity.setFinishedAt(finishedAt);
        membershipPlanEntity.setUpdatedAt(new Date());

        return this.membershipPlanService.save(membershipPlanEntity);
    }

    public Page<MembershipPlanEntity> getAll(Pageable pageable) {
        return this.membershipPlanService.findAll(pageable);
    }

    public MembershipPlanEntity getById(Long id) {
        Optional<MembershipPlanEntity> optional = this.membershipPlanService.findById(id);

        if (optional.isEmpty()) {
            throw new MembershipPlanNotFoundException();
        }

        return optional.get();
    }

}
