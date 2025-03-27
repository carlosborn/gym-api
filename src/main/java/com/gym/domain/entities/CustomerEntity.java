package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "customers")
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String document;

    @Column
    private CustomerGender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private Double weight;

    @Column
    private Double height;

    @Column(name = "access_code")
    private int accessCode;

    @Column
    private CustomerStatus status;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(targetEntity = AddressEntity.class)
    private AddressEntity address;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
