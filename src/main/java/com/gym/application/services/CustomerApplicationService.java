package com.gym.application.services;

import com.gym.domain.entities.AddressEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.CustomerGender;
import com.gym.domain.exceptions.CustomerNotFoundException;
import com.gym.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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

    public CustomerEntity updateCustomer(Long id, String name, String document, CustomerGender customerGender,
                                         Date birthDate, Double weight, Double height, AddressEntity addressEntity) {

        CustomerEntity customerEntity = this.getById(id);
        customerEntity.setName(name);
        customerEntity.setDocument(document);
        customerEntity.setGender(customerGender);
        customerEntity.setBirthDate(birthDate);
        customerEntity.setWeight(weight);
        customerEntity.setHeight(height);
        customerEntity.setAddress(addressEntity);

        return this.customerService.save(customerEntity);
    }

    public CustomerEntity getById(Long id) {
        Optional<CustomerEntity> optional = this.customerService.findById(id);

        if (optional.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        return optional.get();
    }

}
