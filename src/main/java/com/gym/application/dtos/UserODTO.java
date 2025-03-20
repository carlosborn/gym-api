package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record UserODTO(Long id, String name, String username, int status, @JsonProperty("created_at") Date createdAt,
                       @JsonProperty("updated_at") Date updatedAt) {
}
