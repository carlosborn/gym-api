package com.gym.infra.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.application.dtos.CustomerAccessIDTO;
import com.gym.application.dtos.CustomerAccessODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.CustomerAccessApplicationService;
import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.infra.helpers.DateHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/customer_access")
@RestController
public class CustomerAccessController {

    @Autowired
    private CustomerAccessApplicationService customerAccessApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{customerId}")
    public ResponseEntity<DefaultPageModel<CustomerAccessODTO>> getAllByCustomer(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10", name = "per_page") int perPage,
                                                                                 @PathVariable Long customerId) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<CustomerAccessEntity> allAccessByCustomer = this.customerAccessApplicationService.getByCustomer(customerId, pageable);

        List<CustomerAccessODTO> allCustomerAccessODTO = new ArrayList<>();
        for (CustomerAccessEntity customerAccessEntity : allAccessByCustomer) {
            allCustomerAccessODTO.add(this.modelMapper.map(customerAccessEntity, CustomerAccessODTO.class));
        }

        DefaultPageModel<CustomerAccessODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allCustomerAccessODTO, pageable, allCustomerAccessODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{customerId}/start_date/{startDate}/final_date/{finalDate}")
    public ResponseEntity<DefaultPageModel<CustomerAccessODTO>> getAllByCustomerAndBetweenDates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10", name = "per_page") int perPage,
            @PathVariable Long customerId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date finalDate) {

        Pageable pageable = PageRequest.of(page, perPage);

        startDate = DateHelper.atStartOfDay(startDate);
        finalDate = DateHelper.atEndOfDay(finalDate);

        Page<CustomerAccessEntity> allAccessByCustomer = this.customerAccessApplicationService.getByCustomerAndBetweenDates(customerId, startDate, finalDate, pageable);

        List<CustomerAccessODTO> allCustomerAccessODTO = new ArrayList<>();
        for (CustomerAccessEntity customerAccessEntity : allAccessByCustomer) {
            allCustomerAccessODTO.add(this.modelMapper.map(customerAccessEntity, CustomerAccessODTO.class));
        }

        DefaultPageModel<CustomerAccessODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allCustomerAccessODTO, pageable, allCustomerAccessODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping("/access")
    public ResponseEntity<String> access(@RequestBody @Validated CustomerAccessIDTO customerAccessIDTO) {
        this.customerAccessApplicationService.validAccessCode(customerAccessIDTO.accessCode());
        return ResponseEntity.ok("");
    }

}
