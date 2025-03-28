package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentODTO {

    private Long id;
    private CustomerEnrollmentODTO customer;

    @JsonProperty("membership_plan")
    private MembershipPlanEnrollmentODTO membershipPlan;

    @JsonProperty("monthly_fee")
    private Double monthlyFee;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("cancellation_date")
    private Date cancellationDate;

    public EnrollmentODTO(){}

}
