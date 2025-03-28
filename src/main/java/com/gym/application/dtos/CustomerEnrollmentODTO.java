package com.gym.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerEnrollmentODTO {

    private Long id;
    private String name;
    private String document;

    public CustomerEnrollmentODTO(){}

}
