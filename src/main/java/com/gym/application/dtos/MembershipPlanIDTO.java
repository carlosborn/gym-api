package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;
import java.util.Date;

public record MembershipPlanIDTO(String name, @JsonProperty("monthly_fee") Double monthlyFee, Integer duration,
                                 int status, @JsonProperty("finished_at") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date finishedAt) {
}
