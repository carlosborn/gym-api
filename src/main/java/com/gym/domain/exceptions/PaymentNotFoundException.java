package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends CustomException {
    public PaymentNotFoundException() {
        super("Payment not found", HttpStatus.NOT_FOUND.value());
    }
}
