package com.gym.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerStatus {

    INACTIVE(0),
    ACTIVE(1),
    BLOCKED(3);

    private final int id;

    public static CustomerStatus getEnum(int id){
        for(CustomerStatus userStatus : CustomerStatus.values()){
            if(userStatus.id == id){
                return userStatus;
            }
        }
        return null;
    }

}
