package com.gym.domain.entities;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDING(0),
    PAID(1),
    CANCELLED(2);

    private final int id;

    PaymentStatus(int id) {
        this.id = id;
    }

    public static PaymentStatus getEnum(int id) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.id == id) {
                return paymentStatus;
            }
        }
        return null;
    }

}
