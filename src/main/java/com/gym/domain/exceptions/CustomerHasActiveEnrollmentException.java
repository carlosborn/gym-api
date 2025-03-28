package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerHasActiveEnrollmentException extends CustomException {
    public CustomerHasActiveEnrollmentException() {
        super("Customer has already an active enrollment", HttpStatus.BAD_REQUEST.value());
    }
}
