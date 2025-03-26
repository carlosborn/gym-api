package com.gym.application.exceptions.handlers;

import com.gym.application.exceptions.ApiError;
import com.gym.domain.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(CustomException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(new ApiError(ex.getStatusCode(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
