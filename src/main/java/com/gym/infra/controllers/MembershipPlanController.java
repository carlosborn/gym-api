package com.gym.infra.controllers;

import com.gym.application.dtos.MembershipPlanIDTO;
import com.gym.application.dtos.MembershipPlanODTO;
import com.gym.application.dtos.UserSessionODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.MembershipPlanApplicationService;
import com.gym.domain.entities.MembershipPlanEntity;
import com.gym.domain.entities.MembershipPlanStatus;
import com.gym.domain.exceptions.MembershipPlanNotCreatedException;
import com.gym.domain.exceptions.MembershipPlanNotUpdatedException;
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

@RequestMapping("/membership_plans")
@RestController
public class MembershipPlanController {

    @Autowired
    private MembershipPlanApplicationService membershipPlanApplicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<DefaultPageModel<MembershipPlanODTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10", name = "per_page") int perPage,
                                                                       @RequestParam(defaultValue = "-1") int status) {
        Pageable pageable = PageRequest.of(page, perPage);

        Page<MembershipPlanEntity> allMembershipPlans = null;
        if (status == -1) {
            allMembershipPlans = this.membershipPlanApplicationService.getAll(pageable);
        } else {
            allMembershipPlans = this.membershipPlanApplicationService.getByStatus(pageable, MembershipPlanStatus.getEnum(status));
        }

        List<MembershipPlanODTO> allMembershipPlansODTO = new ArrayList<>();
        for (MembershipPlanEntity membershipPlanEntity : allMembershipPlans) {
            allMembershipPlansODTO.add(this.modelMapper.map(membershipPlanEntity, MembershipPlanODTO.class));
        }

        DefaultPageModel<MembershipPlanODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allMembershipPlansODTO, pageable, allMembershipPlansODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlanODTO> getById(@PathVariable Long id) {
        MembershipPlanEntity membershipPlanEntity = this.membershipPlanApplicationService.getById(id);

        MembershipPlanODTO membershipPlanODTO = this.modelMapper.map(membershipPlanEntity, MembershipPlanODTO.class);

        return ResponseEntity.ok(membershipPlanODTO);
    }

    @PostMapping
    public ResponseEntity<String> createMembershipPlan(@RequestBody @Validated MembershipPlanIDTO membershipPlanIDTO) {
        MembershipPlanEntity membershipPlan = this.membershipPlanApplicationService.createMembershipPlan(
                membershipPlanIDTO.name(),
                membershipPlanIDTO.monthlyFee(),
                membershipPlanIDTO.duration(),
                MembershipPlanStatus.ACTIVE
        );

        if (membershipPlan.getId() == null) {
            throw new MembershipPlanNotCreatedException();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMembershipPlan(@PathVariable Long id, @RequestBody MembershipPlanIDTO membershipPlanIDTO) {
        MembershipPlanEntity membershipPlan = this.membershipPlanApplicationService.updateMembershipPlan(
                id,
                membershipPlanIDTO.name(),
                membershipPlanIDTO.monthlyFee(),
                membershipPlanIDTO.duration(),
                MembershipPlanStatus.getEnum(membershipPlanIDTO.status()),
                membershipPlanIDTO.finishedAt()
        );

        if (membershipPlan.getId() == null) {
            throw new MembershipPlanNotUpdatedException();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
