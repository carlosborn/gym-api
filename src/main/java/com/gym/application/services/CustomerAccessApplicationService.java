package com.gym.application.services;

import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.exceptions.InvalidAccessCodeException;
import com.gym.domain.services.CustomerAccessService;
import com.gym.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class CustomerAccessApplicationService {

    @Autowired
    private CustomerAccessService customerAccessService;

    @Autowired
    private CustomerApplicationService customerApplicationService;

    public Page<CustomerAccessEntity> getByCustomer(Long customerId, Pageable pageable) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        return this.customerAccessService.findByCustomer(customerEntity, pageable);
    }

    public Page<CustomerAccessEntity> getByCustomerAndBetweenDates(Long customerId, Date start, Date end, Pageable pageable) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        return this.customerAccessService.findByCustomerAndCreatedAtBetween(customerEntity, start, end, pageable);
    }

    public void validAccessCode(Integer accessCode) {
        if (accessCode <= 0) {
            throw new InvalidAccessCodeException();
        }

        CustomerEntity customerEntity = this.customerApplicationService.getByAccessCode(accessCode);

        CustomerAccessEntity customerAccessEntity = this.createCustomerAccess(customerEntity);
    }

    private CustomerAccessEntity createCustomerAccess(CustomerEntity customerEntity) {
        CustomerAccessEntity customerAccessEntity = new CustomerAccessEntity();
        customerAccessEntity.setCustomer(customerEntity);
        customerAccessEntity.setCreatedAt(new Date());

        return this.customerAccessService.save(customerAccessEntity);
    }

}
