package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record PaymentCancelAllIDTO(@JsonProperty("cancel_date")Date cancelDate) {
}
