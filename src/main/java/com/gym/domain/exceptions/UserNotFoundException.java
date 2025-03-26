package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public final class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("User not found.", HttpStatus.NOT_FOUND.value());
    }

}
