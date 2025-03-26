package com.gym.domain.exceptions;

public class MembershipPlanNotFoundException extends RuntimeException {
    public MembershipPlanNotFoundException() {
        super("Membership plan not found.");
    }
}
