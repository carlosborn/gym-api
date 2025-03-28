package com.gym.infra.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.application.dtos.PaymentIDTO;
import com.gym.application.services.EnrollmentApplicationService;
import com.gym.application.services.PaymentApplicationService;
import com.gym.domain.exceptions.PaymentNotPaidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private PaymentApplicationService paymentApplicationService;

    @Autowired
    private EnrollmentApplicationService enrollmentApplicationService;

    @PostMapping("/{id}")
    public ResponseEntity<String> pay(@PathVariable Long id, @RequestBody PaymentIDTO paymentIDTO) {
        this.paymentApplicationService.pay(id, paymentIDTO.paidDate());
        return ResponseEntity.ok("");
    }

}
