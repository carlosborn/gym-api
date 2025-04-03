package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerAccessNotFoundException extends CustomException {
    public CustomerAccessNotFoundException() {
        super("No access found", HttpStatus.NOT_FOUND.value());
    }
}
