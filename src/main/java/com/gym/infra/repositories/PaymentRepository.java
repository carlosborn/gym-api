package com.gym.infra.repositories;

import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.PaymentEntity;
import com.gym.domain.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByEnrollment(EnrollmentEntity enrollmentEntity);

    List<PaymentEntity> findByEnrollmentAndStatus(EnrollmentEntity enrollmentEntity, PaymentStatus paymentStatus);

}
