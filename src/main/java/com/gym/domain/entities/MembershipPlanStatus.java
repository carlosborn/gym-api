package com.gym.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipPlanStatus {

    INACTIVE(0),
    ACTIVE(1);

    private final int id;

    public static MembershipPlanStatus getEnum(int id){
        for(MembershipPlanStatus userStatus : MembershipPlanStatus.values()){
            if(userStatus.id == id){
                return userStatus;
            }
        }
        return null;
    }

}
