package com.gym.application.services;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.exceptions.CustomerHasActiveEnrollmentException;
import com.gym.domain.exceptions.EnrollmentNotFoundException;
import com.gym.domain.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class EnrollmentApplicationService {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CustomerApplicationService customerApplicationService;

    @Autowired
    private MembershipPlanApplicationService membershipPlanApplicationService;

    @Autowired
    private PaymentApplicationService paymentApplicationService;

    public EnrollmentEntity createEnrollment(Long customerId, Long membershipPlanId, LocalDate dueDate) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        MembershipPlanEntity membershipPlanEntity = this.membershipPlanApplicationService.getById(membershipPlanId);

        if (this.enrollmentService.findByCustomerAndCancellationDateIsNull(customerEntity).isPresent()) {
            throw new CustomerHasActiveEnrollmentException();
        }

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setCustomer(customerEntity);
        enrollmentEntity.setMembershipPlan(membershipPlanEntity);
        enrollmentEntity.setMonthlyFee(membershipPlanEntity.getMonthlyFee());
        enrollmentEntity.setCreatedAt(new Date());

        enrollmentEntity = this.enrollmentService.save(enrollmentEntity);

        this.paymentApplicationService.createPayments(enrollmentEntity, dueDate);

        return enrollmentEntity;
    }

    public Page<EnrollmentEntity> getAll(Pageable pageable) {
        return this.enrollmentService.findAll(pageable);
    }

    public EnrollmentEntity getById(Long id) {
        Optional<EnrollmentEntity> optional = this.enrollmentService.findById(id);

        if (optional.isEmpty()) {
            throw new EnrollmentNotFoundException();
        }

        return optional.get();
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
