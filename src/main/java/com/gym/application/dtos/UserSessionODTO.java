package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record UserSessionODTO(Long id, @JsonProperty("user") UserODTO userODTO, @JsonProperty("created_at") Date createdAt) {
}
