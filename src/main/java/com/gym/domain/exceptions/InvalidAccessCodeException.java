package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidAccessCodeException extends CustomException {
    public InvalidAccessCodeException() {
        super("Invalid access code", HttpStatus.BAD_REQUEST.value());
    }
}
