package com.gym.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerGender {

    MALE(0),
    FEMALE(1),
    OTHER(2);

    private final int id;

    public static CustomerGender getEnum(int id) {
        for (CustomerGender customerGender : CustomerGender.values()) {
            if (customerGender.getId() == id) {
                return customerGender;
            }
        }
        return null;
    }

}
