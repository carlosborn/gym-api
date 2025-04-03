package com.gym.application.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CustomerAccessStatisticODTO {

    @JsonProperty("days_attended")
    private int daysAttended;
    private CustomerEnrollmentODTO customer;
    @JsonProperty("last_access")
    private Date lastAccess;

    public CustomerAccessStatisticODTO() {
    }

}
