package com.gym.infra.repositories;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByName(String name);

    Page<UserEntity> findAllByStatus(UserStatus userStatus, Pageable pageable);

}
