package com.gym.application.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public final class ApiError {

    @JsonProperty("status_code")
    private final int statusCode;

    private final String message;

    @JsonProperty("reference_date")
    private final Date referenceDate;

    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.referenceDate = new Date();
    }

}
