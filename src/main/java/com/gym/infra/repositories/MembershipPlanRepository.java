package com.gym.infra.repositories;

import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.MembershipPlanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlanEntity, Long> {

    Page<MembershipPlanEntity> findByStatus(Pageable pageable, MembershipPlanStatus membershipPlanStatus);

}
