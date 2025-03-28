package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "payments")
@Table(name = "payments")
@Getter
@Setter
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = EnrollmentEntity.class)
    private EnrollmentEntity enrollment;

    @Column
    private Double value;

    @Column
    private PaymentStatus status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "paid_at")
    private Date paidAt;

}
