package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;

public record CustomerIDTO(
        @NotNull(message = "Name is mandatory") String name,
        @NotNull(message = "Name is mandatory")
        String document,
        @NotNull(message = "Name is mandatory")
        @PositiveOrZero(message = "Gender can only be 0 or 1")
        int gender,
        @NotNull(message = "Birth date is mandatory") @JsonProperty("birth_date") LocalDate birthDate,
        @NotNull(message = "Weight is mandatory")
        @PositiveOrZero(message = "Weight must be positive or zero")
        Double weight,
        @NotNull(message = "Height is mandatory")
        @PositiveOrZero(message = "Height must be positive or zero")
        Double height,
        @NotNull(message = "Status is mandatory")
        @PositiveOrZero(message = "Status can only be 0 or 1")
        int status, @Validated AddressIDTO address) {
}
