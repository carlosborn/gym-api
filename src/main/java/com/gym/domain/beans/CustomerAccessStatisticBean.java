package com.gym.domain.beans;

import com.gym.domain.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomerAccessStatisticBean {

    private int daysAttended;
    private CustomerEntity customer;
    private Date lastAccess;

}
