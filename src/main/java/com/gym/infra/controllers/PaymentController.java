package com.gym.infra.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.application.dtos.PaymentCancelAllIDTO;
import com.gym.application.dtos.PaymentIDTO;
import com.gym.application.services.EnrollmentApplicationService;
import com.gym.application.services.PaymentApplicationService;
import com.gym.domain.entities.EnrollmentEntity;
import com.gym.domain.entities.PaymentEntity;
import com.gym.domain.exceptions.PaymentNotPaidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private PaymentApplicationService paymentApplicationService;

    @Autowired
    private EnrollmentApplicationService enrollmentApplicationService;

    @PostMapping("/{id}/pay")
    public ResponseEntity<String> pay(@PathVariable Long id, @RequestBody @Validated PaymentIDTO paymentIDTO) {
        this.paymentApplicationService.pay(id, paymentIDTO.paidDate());
        return ResponseEntity.ok("");
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancel(@PathVariable Long id, @RequestBody @Validated PaymentIDTO paymentIDTO) {
        PaymentEntity paymentEntity = this.paymentApplicationService.getById(id);
        this.paymentApplicationService.cancel(paymentEntity);
        return ResponseEntity.ok("");
    }

    @PostMapping("/enrollment/{enrollmentId}/cancel_all")
    public ResponseEntity<String> cancel(@PathVariable Long enrollmentId, @RequestBody PaymentCancelAllIDTO paymentCancelAllIDTO) {
        EnrollmentEntity enrollmentEntity = this.enrollmentApplicationService.getById(enrollmentId);
        this.paymentApplicationService.cancelAllByEnrollment(enrollmentEntity);
        return ResponseEntity.ok("");
    }

}
