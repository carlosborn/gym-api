package com.gym.domain.services;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.infra.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public EnrollmentEntity save(EnrollmentEntity enrollmentEntity) {
        return this.enrollmentRepository.save(enrollmentEntity);
    }

    public Page<EnrollmentEntity> findAll(Pageable pageable) {
        return this.enrollmentRepository.findAll(pageable);
    }

    public Optional<EnrollmentEntity> findById(Long id) {
        return this.enrollmentRepository.findById(id);
    }

    public Page<EnrollmentEntity> findAllCancelled(Pageable pageable) {
        return this.enrollmentRepository.findByCancellationDateIsNotNull(pageable);
    }

    public Page<EnrollmentEntity> findByMembershipPlan(MembershipPlanEntity membershipPlanEntity, Pageable pageable) {
        return this.enrollmentRepository.findByMembershipPlan(membershipPlanEntity, pageable);
    }

    public Page<EnrollmentEntity> findByCustomer(CustomerEntity customerEntity, Pageable pageable) {
        return this.enrollmentRepository.findByCustomer(customerEntity, pageable);
    }

}
