package com.gym.application.services;

import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.services.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MembershipPlanApplicationService {

    @Autowired
    private MembershipPlanService membershipPlanService;

    public Page<MembershipPlanEntity> getAll(Pageable pageable) {
        return this.membershipPlanService.findAll(pageable);
    }

}
