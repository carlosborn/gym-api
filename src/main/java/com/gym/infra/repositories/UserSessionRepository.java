package com.gym.infra.repositories;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserSessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {

    Page<UserSessionEntity> findByUser(UserEntity userEntity, Pageable pageable);

}
