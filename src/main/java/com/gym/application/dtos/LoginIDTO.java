package com.gym.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record LoginIDTO(@NotNull(message = "Username not provided") String username,
                        @NotNull(message = "Password not provided") String password) {
}
