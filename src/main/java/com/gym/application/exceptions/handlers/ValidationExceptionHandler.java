package com.gym.application.exceptions.handlers;

import com.gym.application.exceptions.ApiError;
import com.gym.domain.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String firstMessage = "";
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            firstMessage = objectError.getDefaultMessage();
            break;
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST.value(), firstMessage));
    }

}
