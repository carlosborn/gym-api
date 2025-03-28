package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentAlreadyPaidException extends CustomException {
    public PaymentAlreadyPaidException() {
        super("Payment already paid", HttpStatus.BAD_REQUEST.value());
    }
}
