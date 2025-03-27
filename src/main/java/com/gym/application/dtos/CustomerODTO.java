package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.domain.entities.CustomerGender;
import com.gym.domain.entities.CustomerStatus;
import com.gym.domain.entities.MembershipPlanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomerODTO {

    private Long id;
    private String name;
    private String document;
    private int gender;

    @JsonProperty("birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Double weight;
    private Double height;

    @JsonProperty("access_code")
    private Integer accessCode;

    private int status;
    private AddressODTO address;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    public CustomerODTO(){}

    public void setStatus(CustomerStatus customerStatus) {
        this.status = customerStatus.getId();
    }

    public void setGender(CustomerGender customerGender) {
        this.gender = customerGender.getId();
    }
}

