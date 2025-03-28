package com.gym.infra.repositories;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

    Page<EnrollmentEntity> findByCustomer(CustomerEntity customerEntity, Pageable pageable);

    Page<EnrollmentEntity> findByMembershipPlan(MembershipPlanEntity membershipPlanEntity, Pageable pageable);

    Page<EnrollmentEntity> findAllCancelled(Pageable pageable);

}
