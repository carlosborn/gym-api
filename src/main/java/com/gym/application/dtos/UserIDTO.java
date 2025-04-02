package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UserIDTO(@NotNull(message = "Name is mandatory") String name,
                       @NotNull(message = "Username is mandatory") String username, String password, int status) {
}
