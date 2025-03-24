package com.gym.domain.services;

import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.UserEntity;
import com.gym.infra.repositories.MembershipPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

}
