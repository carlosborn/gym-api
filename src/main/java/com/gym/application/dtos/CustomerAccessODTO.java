package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomerAccessODTO {

    private Long id;
    private CustomerEnrollmentODTO customer;

    @JsonProperty("created_at")
    private Date createdAt;

    public CustomerAccessODTO(){}

}
