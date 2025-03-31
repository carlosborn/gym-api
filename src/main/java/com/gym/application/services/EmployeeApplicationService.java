package com.gym.application.services;

import com.gym.domain.entities.EmployeeEntity;
import com.gym.domain.entities.EmployeeStatus;
import com.gym.domain.entities.UserEntity;
import com.gym.domain.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeApplicationService {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeEntity createEmployee(String name, String document, UserEntity userEntity) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(name);
        employeeEntity.setDocument(document);
        employeeEntity.setStatus(EmployeeStatus.ACTIVE);
        employeeEntity.setUser(userEntity);
        employeeEntity.setCreatedAt(new Date());
        employeeEntity.setUpdatedAt(new Date());

        return this.employeeService.save(employeeEntity);
    }

}
