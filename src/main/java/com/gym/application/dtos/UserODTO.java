package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.domain.entities.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserODTO {

    private Long id;
    private String name;
    private int status;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    public UserODTO() {
    }

    public void setStatus(UserStatus userStatus) {
        this.status = userStatus.getId();
    }
}
