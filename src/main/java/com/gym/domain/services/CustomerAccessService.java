package com.gym.domain.services;

import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.infra.repositories.CustomerAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccessService {

    @Autowired
    private CustomerAccessRepository customerAccessRepository;

    public CustomerAccessEntity save(CustomerAccessEntity customerAccessEntity) {
        return this.customerAccessRepository.save(customerAccessEntity);
    }

    public Page<CustomerAccessEntity> findByCustomer(CustomerEntity customer, Pageable pageable) {
        return this.customerAccessRepository.findByCustomer(customer, pageable);
    }

}
