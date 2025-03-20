package com.gym.domain.exceptions;

public final class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }

}
