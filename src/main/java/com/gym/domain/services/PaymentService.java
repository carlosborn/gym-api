package com.gym.domain.services;

import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.PaymentEntity;
import com.gym.domain.entities.PaymentStatus;
import com.gym.infra.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentEntity save(PaymentEntity paymentEntity) {
        return this.paymentRepository.save(paymentEntity);
    }

    public Optional<PaymentEntity> findById(Long id) {
        return this.paymentRepository.findById(id);
    }

    public List<PaymentEntity> findByEnrollment(EnrollmentEntity enrollmentEntity) {
        return this.paymentRepository.findByEnrollment(enrollmentEntity);
    }

    public List<PaymentEntity> findByEnrollmentAndStatus(EnrollmentEntity enrollmentEntity, PaymentStatus status) {
        return this.paymentRepository.findByEnrollmentAndStatus(enrollmentEntity, status);
    }

}
