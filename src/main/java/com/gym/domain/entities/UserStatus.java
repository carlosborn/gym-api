package com.gym.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    INACTIVE(0),
    ACTIVE(1),
    BLOCKED(3);

    private final int id;

    public static UserStatus getEnum(int id){
        for(UserStatus userStatus : UserStatus.values()){
            if(userStatus.id == id){
                return userStatus;
            }
        }
        return null;
    }

}
