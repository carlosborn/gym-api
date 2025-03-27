package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends CustomException {
    public CustomerNotFoundException() {
        super("Customer not found", HttpStatus.NOT_FOUND.value());
    }
}
