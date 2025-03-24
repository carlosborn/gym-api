package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "membership_plans")
@Table(name = "membership_plans")
@Getter
@Setter
public class MembershipPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "monthly_fee")
    private Double monthlyFee;

    @Column
    private Integer duration;

    @Column
    private MembershipPlanStatus status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "finished_at")
    private Date finishedAt;

}
