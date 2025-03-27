package com.gym.infra.repositories;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByDocument(String document);
    Page<CustomerEntity> findByStatus(Pageable pageable, CustomerStatus status);
}
