package com.gym.application.dtos;

import lombok.NonNull;

public record LoginIDTO(@NonNull String username, @NonNull String password) {
}
