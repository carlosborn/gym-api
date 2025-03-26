package com.gym.application.services;

import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.CustomerGender;
import com.gym.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerApplicationService {

    @Autowired
    private CustomerService customerService;

    public CustomerEntity createCustomer(String name, String document, CustomerGender customerGender,
                                         Date birthDate, Double weight, Double height) {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setDocument(document);
        customerEntity.setGender(customerGender);
        customerEntity.setBirthDate(birthDate);
        customerEntity.setWeight(weight);
        customerEntity.setHeight(height);

        return this.customerService.save(customerEntity);
    }

}
