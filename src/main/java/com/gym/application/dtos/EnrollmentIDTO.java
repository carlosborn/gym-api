package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EnrollmentIDTO (@JsonProperty("customer_id") Long customerId, @JsonProperty("membership_plan_id") Long membershipPlanId) {
}
