package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public record CustomerIDTO(String name, String document, int gender, @JsonProperty("birth_date") LocalDate birthDate,
                           Double weight, Double height, int status, AddressIDTO address) {
}
