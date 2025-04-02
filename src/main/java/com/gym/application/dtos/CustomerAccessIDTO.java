package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerAccessIDTO(
        @JsonProperty("access_code")
        @NotNull(message = "Access code is mandatory")
        @Size(message = "Invalid access code")
        Integer accessCode) {
}
