package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class EnrollmentNotFoundException extends CustomException {
    public EnrollmentNotFoundException() {
        super("Enrollment not found", HttpStatus.NOT_FOUND.value());
    }
}
