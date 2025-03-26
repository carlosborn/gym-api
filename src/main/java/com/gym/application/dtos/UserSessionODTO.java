package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserSessionODTO {

    private Long id;

    @JsonProperty("user")
    private UserODTO userODTO;

    @JsonProperty("created_at")
    private Date createdAt;

    public UserSessionODTO() {
    }

}
