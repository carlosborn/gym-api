package com.gym.application.exceptions.handlers;

import com.gym.application.exceptions.ApiError;
import com.gym.domain.exceptions.MembershipPlanNotCreatedException;
import com.gym.domain.exceptions.MembershipPlanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MembershipPlanExceptionHandler {

    @ExceptionHandler(MembershipPlanNotFoundException.class)
    public ResponseEntity<ApiError> handleMembershipPlanNotFoundException(MembershipPlanNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(MembershipPlanNotCreatedException.class)
    public ResponseEntity<ApiError> handleMembershipPlanNotCreatedException(MembershipPlanNotCreatedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

}
