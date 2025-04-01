package com.gym.infra.controllers;

import com.gym.application.dtos.EmployeeIDTO;
import com.gym.application.dtos.EmployeeODTO;
import com.gym.application.dtos.EnrollmentODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.EmployeeApplicationService;
import com.gym.application.services.UserApplicationService;
import com.gym.domain.entities.EmployeeEntity;
import com.gym.domain.entities.EmployeeStatus;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeApplicationService employeeApplicationService;

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<DefaultPageModel<EmployeeODTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10", name = "per_page") int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<EmployeeEntity> allEmployees = this.employeeApplicationService.getAll(pageable);

        List<EmployeeODTO> allEmployeesODTO = new ArrayList<>();
        for (EmployeeEntity employeeEntity : allEmployees) {
            allEmployeesODTO.add(this.modelMapper.map(employeeEntity, EmployeeODTO.class));
        }

        DefaultPageModel<EmployeeODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allEmployeesODTO, pageable, allEmployeesODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeODTO> getById(@PathVariable Long id) {
        EmployeeEntity employeeEntity = this.employeeApplicationService.getById(id);
        EmployeeODTO employeeODTO = this.modelMapper.map(employeeEntity, EmployeeODTO.class);
        return ResponseEntity.ok(employeeODTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeODTO> createEmployee(@RequestBody @Validated EmployeeIDTO employeeIDTO) {

        UserEntity userEntity = this.userApplicationService.getUserById(employeeIDTO.userId());

        EmployeeEntity employeeEntity = this.employeeApplicationService.createEmployee(
                employeeIDTO.name(),
                employeeIDTO.document(),
                userEntity
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeODTO> updateEmployee(@RequestBody @Validated EmployeeIDTO employeeIDTO) {

        UserEntity userEntity = this.userApplicationService.getUserById(employeeIDTO.userId());

        EmployeeEntity employeeEntity = this.employeeApplicationService.updateEmployee(
                employeeIDTO.name(),
                employeeIDTO.document(),
                userEntity,
                EmployeeStatus.getEnum(employeeIDTO.status())
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
