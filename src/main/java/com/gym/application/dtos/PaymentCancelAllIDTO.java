package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PaymentCancelAllIDTO(
        @JsonProperty("cancel_date") @NotNull(message = "Cancel date is mandatory") Date cancelDate) {
}
