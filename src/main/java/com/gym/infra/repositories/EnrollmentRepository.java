package com.gym.infra.repositories;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    Page<EnrollmentEntity> findByCustomer(CustomerEntity customerEntity, Pageable pageable);

    Page<EnrollmentEntity> findByMembershipPlan(MembershipPlanEntity membershipPlanEntity, Pageable pageable);

    // @Query(value = "SELECT * FROM enrollments e WHERE e.cancellation_date IS NOT NULL", nativeQuery = true)
    Page<EnrollmentEntity> findByCancellationDateIsNotNull(Pageable pageable);

    Optional<EnrollmentEntity> findByCustomerAndCancellationDateIsNull(CustomerEntity customerEntity);

}
