package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.domain.entities.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeODTO {

    private Long id;
    private String name;
    private String document;
    private int status;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    public EmployeeODTO() {
    }

    public void setStatus(EmployeeStatus employeeStatus) {
        this.status = employeeStatus.getId();
    }

}
