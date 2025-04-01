package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EmployeeIDTO(
        @NotNull(message = "Name is mandatory")
        String name,
        @NotNull(message = "Document is mandatory")
        String document,
        @NotNull(message = "Status is mandatory")
        @PositiveOrZero(message = "Status can only be 0 or 1")
        int status,
        @JsonProperty("user_id") Long userId) {
}
