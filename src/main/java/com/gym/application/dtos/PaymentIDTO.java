package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PaymentIDTO(@JsonProperty("paid_date") @NotNull(message = "Payment date is mandatory") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date paidDate) {
}
