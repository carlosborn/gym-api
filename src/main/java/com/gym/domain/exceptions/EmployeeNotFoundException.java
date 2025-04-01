package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends CustomException {
    public EmployeeNotFoundException() {
        super("Employee not found", HttpStatus.NOT_FOUND.value());
    }
}
