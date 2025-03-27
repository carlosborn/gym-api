package com.gym.infra.controllers;

import com.gym.application.dtos.AddressIDTO;
import com.gym.application.dtos.CustomerIDTO;
import com.gym.application.dtos.CustomerODTO;
import com.gym.application.dtos.MembershipPlanODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.AddressApplicationService;
import com.gym.application.services.CustomerApplicationService;
import com.gym.domain.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerApplicationService customerApplicationService;

    @Autowired
    private AddressApplicationService addressApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<DefaultPageModel<CustomerODTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10", name = "per_page") int perPage,
                                                                 @RequestParam(defaultValue = "-1") int status) {

        Pageable pageable = PageRequest.of(page, perPage);

        Page<CustomerEntity> allCustomers = null;
        if (status == -1) {
            allCustomers = this.customerApplicationService.getAll(pageable);
        } else {
            allCustomers = this.customerApplicationService.getByStatus(pageable, CustomerStatus.getEnum(status));
        }

        List<CustomerODTO> allCustomersODTO = new ArrayList<>();
        for (CustomerEntity customerEntity : allCustomers) {
            allCustomersODTO.add(this.modelMapper.map(customerEntity, CustomerODTO.class));
        }

        DefaultPageModel<CustomerODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allCustomersODTO, pageable, allCustomersODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerODTO> getById(@PathVariable Long id) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(id);
        CustomerODTO customerODTO = this.modelMapper.map(customerEntity, CustomerODTO.class);
        return ResponseEntity.ok(customerODTO);
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<CustomerODTO> getById(@PathVariable String document) {
        CustomerEntity customerEntity = this.customerApplicationService.getByDocument(document);
        CustomerODTO customerODTO = this.modelMapper.map(customerEntity, CustomerODTO.class);
        return ResponseEntity.ok(customerODTO);
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerIDTO customerIDTO) {
        CustomerEntity customerEntity = this.customerApplicationService.createCustomer(
                customerIDTO.name(),
                customerIDTO.document(),
                CustomerGender.getEnum(customerIDTO.gender()),
                customerIDTO.birthDate(),
                customerIDTO.weight(),
                customerIDTO.height()
        );

        AddressIDTO addressIDTO = customerIDTO.address();

        AddressEntity addressEntity = this.addressApplicationService.createAddress(
                addressIDTO.street(),
                addressIDTO.number(),
                addressIDTO.complement(),
                addressIDTO.neighborhood(),
                addressIDTO.city(),
                addressIDTO.state(),
                addressIDTO.zipcode(),
                customerEntity
        );

        this.customerApplicationService.addCustomerAddress(customerEntity, addressEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
