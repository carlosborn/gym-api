package com.gym.application.services;

import com.gym.domain.entities.AddressEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.CustomerGender;
import com.gym.domain.entities.CustomerStatus;
import com.gym.domain.exceptions.CustomerNotFoundException;
import com.gym.domain.exceptions.DocumentCustomerAlreadyExists;
import com.gym.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class CustomerApplicationService {

    @Autowired
    private CustomerService customerService;

    public CustomerEntity createCustomer(String name, String document, CustomerGender customerGender,
                                         LocalDate birthDate, Double weight, Double height) {

        if (this.isDocumentDuplicated(document, null)) {
            throw new DocumentCustomerAlreadyExists();
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setDocument(this.cleanDocument(document));
        customerEntity.setGender(customerGender);
        customerEntity.setBirthDate(birthDate);
        customerEntity.setWeight(weight);
        customerEntity.setHeight(height);
        customerEntity.setStatus(CustomerStatus.ACTIVE);
        customerEntity.setCreatedAt(new Date());
        customerEntity.setUpdatedAt(new Date());
        customerEntity.setAccessCode(this.createAccessCode());

        return this.customerService.save(customerEntity);
    }

    public CustomerEntity updateCustomer(Long id, String name, String document, CustomerGender customerGender,
                                         LocalDate birthDate, Double weight, Double height, CustomerStatus customerStatus) {

        CustomerEntity customerEntity = this.getById(id);

        if (this.isDocumentDuplicated(document, customerEntity)) {
            throw new DocumentCustomerAlreadyExists();
        }

        customerEntity.setName(name);
        customerEntity.setDocument(this.cleanDocument(document));
        customerEntity.setGender(customerGender);
        customerEntity.setBirthDate(birthDate);
        customerEntity.setWeight(weight);
        customerEntity.setHeight(height);
        customerEntity.setStatus(customerStatus);
        customerEntity.setUpdatedAt(new Date());

        return this.customerService.save(customerEntity);
    }

    public void addCustomerAddress(CustomerEntity customerEntity, AddressEntity addressEntity) {
        customerEntity.setAddress(addressEntity);
        this.customerService.save(customerEntity);
    }

    public Page<CustomerEntity> getAll(Pageable pageable) {
        return this.customerService.findAll(pageable);
    }

    public CustomerEntity getById(Long id) {
        Optional<CustomerEntity> optional = this.customerService.findById(id);

        if (optional.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        return optional.get();
    }

    public CustomerEntity getByDocument(String document) {
        Optional<CustomerEntity> optional = this.customerService.findByDocument(document);

        if (optional.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        return optional.get();
    }

    public Page<CustomerEntity> getByStatus(Pageable pageable, CustomerStatus status) {
        return this.customerService.findByStatus(pageable, status);
    }

    private Integer createAccessCode() {
        Integer accessCode;
        Random random = new Random();
        do {
            accessCode = 100000 + random.nextInt(900000);
        } while (this.customerService.findByAccessCode(accessCode).isPresent());
        return accessCode;
    }

    private boolean isDocumentDuplicated(String document, CustomerEntity customerEntity) {
        Optional<CustomerEntity> optional = this.customerService.findByDocument(document);

        // Nao achou nenhum cliente com esse documento, permite a alteracao
        if (optional.isEmpty()) {
            return false;
        }

        CustomerEntity customerEntityComparator = optional.get();

        if (customerEntity == null) {
            return true;
        }

        // Os IDs do cliente sao iguais, entao permite a alteracao
        return !Objects.equals(customerEntityComparator.getId(), customerEntity.getId());
    }

    private String cleanDocument(String document) {
        return document.replaceAll("[.-]", "");
    }

}
