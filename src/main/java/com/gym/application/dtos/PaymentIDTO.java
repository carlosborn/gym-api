package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record PaymentIDTO(@JsonProperty("paid_date") @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date paidDate) {
}
