package com.gym.infra.controllers;

import com.gym.application.dtos.CustomerODTO;
import com.gym.application.dtos.MembershipPlanODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.AddressApplicationService;
import com.gym.application.services.CustomerApplicationService;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.MembershipPlanStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            // allCustomers = this.customerApplicationService.getByStatus(pageable, MembershipPlanStatus.getEnum(status));
        }

        List<CustomerODTO> allCustomersODTO = new ArrayList<>();
        for (CustomerEntity customerEntity : allCustomers) {
            allCustomersODTO.add(this.modelMapper.map(customerEntity, CustomerODTO.class));
        }

        DefaultPageModel<CustomerODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allCustomersODTO, pageable, allCustomersODTO.size()));

        return ResponseEntity.ok(pagedModel);

    }

}
