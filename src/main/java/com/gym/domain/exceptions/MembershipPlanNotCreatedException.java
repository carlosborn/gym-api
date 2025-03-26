package com.gym.domain.exceptions;

public class MembershipPlanNotCreatedException extends RuntimeException {
    public MembershipPlanNotCreatedException() {
        super("An error occurred on create membership plan.");
    }
}
