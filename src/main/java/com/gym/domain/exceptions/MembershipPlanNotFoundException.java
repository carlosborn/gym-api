package com.gym.domain.exceptions;

import org.springframework.http.HttpStatus;

public class MembershipPlanNotFoundException extends CustomException {
    public MembershipPlanNotFoundException() {
        super("Membership plan not found.", HttpStatus.NOT_FOUND.value());
    }
}
