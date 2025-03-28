package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentNotCreatedException extends CustomException {
    public PaymentNotCreatedException() {
        super("Payment not created", HttpStatus.BAD_REQUEST.value());
    }
}
