package com.gym.application.exceptions.handlers;

import com.gym.application.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiError> handleSQLException(SQLException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

}
