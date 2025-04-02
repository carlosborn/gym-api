package com.gym.domain.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "customer_accesses")
@Entity(name = "customer_accesses")
@Getter
@Setter
public class CustomerAccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = CustomerEntity.class)
    private CustomerEntity customer;

    @Column(name = "created_at")
    private Date createdAt;

}
