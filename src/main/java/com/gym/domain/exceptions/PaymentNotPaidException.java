package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class PaymentNotPaidException extends CustomException {
    public PaymentNotPaidException() {
        super("An error occurred when paying one or more monthly fees", HttpStatus.BAD_REQUEST.value());
    }
}
