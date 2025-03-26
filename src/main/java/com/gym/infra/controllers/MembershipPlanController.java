package com.gym.infra.controllers;

import com.gym.application.dtos.MembershipPlanODTO;
import com.gym.application.dtos.UserSessionODTO;
import com.gym.application.pageables.DefaultPageModel;
import com.gym.application.services.MembershipPlanApplicationService;
import com.gym.domain.entities.MembershipPlanEntity;
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

@RequestMapping("/membership_plans")
@RestController
public class MembershipPlanController {

    @Autowired
    private MembershipPlanApplicationService membershipPlanApplicationService;

    @GetMapping
    public ResponseEntity<DefaultPageModel<MembershipPlanODTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10", name = "per_page") int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<MembershipPlanEntity> allMembershipPlans = this.membershipPlanApplicationService.getAll(pageable);

        List<MembershipPlanODTO> allMembershipPlansODTO = new ArrayList<>();
        for (MembershipPlanEntity membershipPlanEntity : allMembershipPlans) {
            allMembershipPlansODTO.add(new MembershipPlanODTO(
                    membershipPlanEntity.getId(),
                    membershipPlanEntity.getName(),
                    membershipPlanEntity.getMonthlyFee(),
                    membershipPlanEntity.getDuration(),
                    membershipPlanEntity.getStatus().getId(),
                    membershipPlanEntity.getCreatedAt(),
                    membershipPlanEntity.getUpdatedAt(),
                    membershipPlanEntity.getFinishedAt()
            ));
        }

        DefaultPageModel<MembershipPlanODTO> pagedModel = new DefaultPageModel<>(new PageImpl<>(allMembershipPlansODTO, pageable, allMembershipPlansODTO.size()));

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlanODTO> getById(@PathVariable Long id) {
        MembershipPlanEntity membershipPlanEntity = this.membershipPlanApplicationService.getById(id);

        MembershipPlanODTO membershipPlanODTO = new MembershipPlanODTO(
                membershipPlanEntity.getId(),
                membershipPlanEntity.getName(),
                membershipPlanEntity.getMonthlyFee(),
                membershipPlanEntity.getDuration(),
                membershipPlanEntity.getStatus().getId(),
                membershipPlanEntity.getCreatedAt(),
                membershipPlanEntity.getUpdatedAt(),
                membershipPlanEntity.getFinishedAt()
        );

        return ResponseEntity.ok(membershipPlanODTO);
    }

}
