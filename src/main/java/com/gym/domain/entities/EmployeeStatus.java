package com.gym.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeStatus {

    INACTIVE(0),
    ACTIVE(1),
    BLOCKED(3);

    private final int id;

    public static EmployeeStatus getEnum(int id) {
        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
            if (employeeStatus.id == id) {
                return employeeStatus;
            }
        }
        return null;
    }

}
