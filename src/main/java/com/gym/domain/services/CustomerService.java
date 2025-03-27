package com.gym.domain.services;

import com.gym.domain.entities.CustomerEntity;
import com.gym.infra.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity save(CustomerEntity customerEntity) {
        return this.customerRepository.save(customerEntity);
    }

    public Page<CustomerEntity> findAll(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    public Optional<CustomerEntity> findById(Long id) {
        return this.customerRepository.findById(id);
    }

}
