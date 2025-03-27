package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class DocumentCustomerAlreadyExists extends CustomException {
    public DocumentCustomerAlreadyExists() {
        super("Document already exists registered with another customer", HttpStatus.BAD_REQUEST.value());
    }
}
