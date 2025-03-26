package com.gym.domain.services;

import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.MembershipPlanStatus;
import com.gym.domain.entities.UserEntity;
import com.gym.infra.repositories.MembershipPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipPlanService {

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    public MembershipPlanEntity save(MembershipPlanEntity membershipPlanEntity) {
        return this.membershipPlanRepository.save(membershipPlanEntity);
    }

    public Page<MembershipPlanEntity> findAll(Pageable pageable) {
        return this.membershipPlanRepository.findAll(pageable);
    }

    public Page<MembershipPlanEntity> findByStatus(Pageable pageable, MembershipPlanStatus membershipPlanStatus) {
        return this.membershipPlanRepository.findByStatus(pageable, membershipPlanStatus);
    }

    public Optional<MembershipPlanEntity> findById(Long id) {
        return this.membershipPlanRepository.findById(id);
    }


}
