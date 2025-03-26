package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.domain.entities.MembershipPlanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MembershipPlanODTO {

    private Long id;
    private String name;

    @JsonProperty("monthly_fee")
    private Double monthlyFee;

    private Integer duration;

    private int status;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("finished_at")
    private Date finishedAt;

    public MembershipPlanODTO(){}

    public void setStatus(MembershipPlanStatus membershipPlanStatus) {
        this.status = membershipPlanStatus.getId();
    }

}
