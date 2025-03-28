package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "enrollments")
@Table(name = "enrollments")
@Getter
@Setter
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = CustomerEntity.class)
    private CustomerEntity customer;

    @JoinColumn(name = "membership_plan_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = MembershipPlanEntity.class)
    private MembershipPlanEntity membershipPlan;

    @Column(name = "monthly_fee")
    private Double monthlyFee;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "cancellation_date")
    private Date cancellationDate;

}

