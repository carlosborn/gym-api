package com.gym.domain.services;

import com.gym.domain.entities.EmployeeEntity;
import com.gym.infra.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return this.employeeRepository.save(employeeEntity);
    }

    public Optional<EmployeeEntity> findById(Long id) {
        return this.employeeRepository.findById(id);
    }

    public List<EmployeeEntity> findAll() {
        return this.employeeRepository.findAll();
    }

}
