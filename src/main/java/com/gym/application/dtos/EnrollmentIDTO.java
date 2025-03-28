package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record EnrollmentIDTO (@JsonProperty("customer_id") Long customerId, @JsonProperty("membership_plan_id") Long membershipPlanId, @JsonProperty("due_date") LocalDate dueDate) {
}
