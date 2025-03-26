package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class MembershipPlanNotCreatedException extends CustomException {
    public MembershipPlanNotCreatedException() {
        super("An error occurred on create membership plan.", HttpStatus.BAD_REQUEST.value());
    }
}
