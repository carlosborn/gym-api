package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressIDTO(
        @NotBlank(message = "Street is mandatory")
        String street,
        @NotBlank(message = "Number is mandatory")
        String number,
        @JsonInclude(JsonInclude.Include.ALWAYS)
        String complement,
        @NotBlank(message = "Neighborhood is mandatory")
        String neighborhood,
        @NotBlank(message = "City is mandatory")
        String city,
        @NotBlank(message = "City is mandatory")
        String state,
        @NotBlank(message = "Zipcode is mandatory")
        String zipcode) {
}
