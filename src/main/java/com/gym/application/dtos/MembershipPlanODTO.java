package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.domain.entities.MembershipPlanStatus;

import java.util.Date;

public record MembershipPlanODTO(Long id, String name, @JsonProperty("monthly_fee") Double monthlyFee, Integer duration,
                                 int status, @JsonProperty("created_at") Date createdAt,
                                 @JsonProperty("updated_at") Date updatedAt,
                                 @JsonProperty("finished_at") Date finishedAt) {
}
