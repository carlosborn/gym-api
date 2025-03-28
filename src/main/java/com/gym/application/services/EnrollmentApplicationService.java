package com.gym.application.services;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EnrollmentApplicationService {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CustomerApplicationService customerApplicationService;

    @Autowired
    MembershipPlanApplicationService membershipPlanApplicationService;

    public EnrollmentEntity createEnrollment(Long customerId, Long membershipPlanId) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        MembershipPlanEntity membershipPlanEntity = this.membershipPlanApplicationService.getById(membershipPlanId);

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setCustomer(customerEntity);
        enrollmentEntity.setMembershipPlan(membershipPlanEntity);
        enrollmentEntity.setMonthlyFee(membershipPlanEntity.getMonthlyFee());
        enrollmentEntity.setCreatedAt(new Date());

        return this.enrollmentService.save(enrollmentEntity);
    }

    public Page<EnrollmentEntity> getAll(Pageable pageable) {
        return this.enrollmentService.findAll(pageable);
    }

    public Optional<EnrollmentEntity> getById(Long id) {
        return this.enrollmentService.findById(id);
    }

    public Page<EnrollmentEntity> getAllCanceled(Pageable pageable) {
        return this.enrollmentService.findAllCancelled(pageable);
    }

    public Page<EnrollmentEntity> getByCustomer(CustomerEntity customerEntity, Pageable pageable) {
        return this.enrollmentService.findByCustomer(customerEntity, pageable);
    }

    public Page<EnrollmentEntity> getByMembershipPlan(MembershipPlanEntity membershipPlanEntity, Pageable pageable) {
        return this.enrollmentService.findByMembershipPlan(membershipPlanEntity, pageable);
    }

}
