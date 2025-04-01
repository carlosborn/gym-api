package com.gym.infra.repositories;

import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.domain.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccessRepository extends JpaRepository<CustomerAccessEntity, Long> {

    Page<CustomerAccessEntity> findByCustomer(CustomerEntity customer, Pageable pageable);

}
