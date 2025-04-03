package com.gym.application.services;

import com.gym.domain.entities.EmployeeEntity;
import com.gym.domain.entities.EmployeeStatus;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.exceptions.EmployeeNotFoundException;
import com.gym.domain.exceptions.InvalidDocumentException;
import com.gym.domain.services.EmployeeService;
import com.gym.infra.helpers.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeApplicationService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserApplicationService userApplicationService;

    public EmployeeEntity createEmployee(String name, String document, Long userId) {

        UserEntity userEntity = this.userApplicationService.getUserById(userId);

        document = DocumentHelper.cleanDocument(document);
        if (!DocumentHelper.isDocumentValid(document)) {
            throw new InvalidDocumentException();
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(name);
        employeeEntity.setDocument(document);
        employeeEntity.setStatus(EmployeeStatus.ACTIVE);
        employeeEntity.setUser(userEntity);
        employeeEntity.setCreatedAt(new Date());
        employeeEntity.setUpdatedAt(new Date());

        return this.employeeService.save(employeeEntity);
    }

    public EmployeeEntity updateEmployee(String name, String document, Long userId, EmployeeStatus status) {

        UserEntity userEntity = this.userApplicationService.getUserById(userId);

        document = DocumentHelper.cleanDocument(document);
        if (!DocumentHelper.isDocumentValid(document)) {
            throw new InvalidDocumentException();
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(name);
        employeeEntity.setDocument(document);
        employeeEntity.setUser(userEntity);
        employeeEntity.setUpdatedAt(new Date());

        if (status != null) {
            employeeEntity.setStatus(status);
        }

        return this.employeeService.save(employeeEntity);
    }

    public Page<EmployeeEntity> getAll(Pageable pageable) {
        return this.employeeService.findAll(pageable);
    }

    public EmployeeEntity getById(Long id) {
        Optional<EmployeeEntity> optional = this.employeeService.findById(id);
        if (optional.isEmpty()) {
            throw new EmployeeNotFoundException();
        }
        return optional.get();
    }

}
