package com.gym.domain.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(){
        super("Username already exists.");
    }

}
