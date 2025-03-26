package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends CustomException {

    public UsernameAlreadyExistsException() {
        super("Username already exists.", HttpStatus.BAD_REQUEST.value());
    }

}
