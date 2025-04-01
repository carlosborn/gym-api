package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import javax.xml.crypto.Data;
import java.util.Date;

public record MembershipPlanIDTO(@NotNull(message = "Name is mandatory") String name,
                                 @NotNull(message = "Monthly fee is mandatory") @JsonProperty("monthly_fee") Double monthlyFee,
                                 @NotNull(message = "Duration is mandatory")
                                 Integer duration,
                                 int status,
                                 @JsonProperty("finished_at") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date finishedAt) {
}
