package com.gym.infra.repositories;

import com.gym.domain.entities.MembershipPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlanEntity, Long> {
}
