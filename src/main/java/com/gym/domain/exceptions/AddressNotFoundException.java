package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends CustomException {
    public AddressNotFoundException() {
        super("Address not found.", HttpStatus.NOT_FOUND.value());
    }
}
