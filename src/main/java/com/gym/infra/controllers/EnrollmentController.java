package com.gym.infra.controllers;

import com.gym.application.dtos.EnrollmentIDTO;
import com.gym.application.dtos.EnrollmentODTO;
import com.gym.application.dtos.MembershipPlanODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.EnrollmentApplicationService;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.MembershipPlanEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/enrollments")
@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentApplicationService enrollmentApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<DefaultPageModel<EnrollmentODTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10", name = "per_page") int perPage) {

        Pageable pageable = PageRequest.of(page, perPage);
        Page<EnrollmentEntity> allEnrollment = this.enrollmentApplicationService.getAll(pageable);

        List<EnrollmentODTO> allEnrollmentODTOs = new ArrayList<>();
        for (EnrollmentEntity enrollmentEntity : allEnrollment) {
            allEnrollmentODTOs.add(this.modelMapper.map(enrollmentEntity, EnrollmentODTO.class));
        }

        DefaultPageModel<EnrollmentODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allEnrollmentODTOs, pageable, allEnrollmentODTOs.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<String> createEnrollment(@RequestBody EnrollmentIDTO enrollmentIDTO) {
        this.enrollmentApplicationService.createEnrollment(
                enrollmentIDTO.customerId(),
                enrollmentIDTO.membershipPlanId(),
                enrollmentIDTO.dueDate());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
