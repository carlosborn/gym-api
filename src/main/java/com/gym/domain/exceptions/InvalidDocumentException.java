package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDocumentException extends CustomException {
    public InvalidDocumentException() {
        super("Invalid document", HttpStatus.BAD_REQUEST.value());
    }
}
