package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class MembershipPlanNotUpdatedException extends CustomException {
    public MembershipPlanNotUpdatedException() {
        super("An error occurred when update the membership plan.", HttpStatus.BAD_REQUEST.value());
    }
}
