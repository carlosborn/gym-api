package com.gym.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record CustomerIDTO(String name, String document, int gender, @JsonProperty("birth_date") Date birthDate,
                           Double weight, Double height, int status, AddressIDTO address) {
}
