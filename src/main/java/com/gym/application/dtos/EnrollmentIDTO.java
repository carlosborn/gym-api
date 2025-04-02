package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EnrollmentIDTO(
        @JsonProperty("customer_id") @NotNull(message = "Customer ID is mandatory") Long customerId,
        @JsonProperty("membership_plan_id") @NotNull(message = "Membership Plan ID is mandatory") Long membershipPlanId,
        @NotNull(message = "Due Date is mandatory")
        @JsonProperty("due_date") LocalDate dueDate) {
}
