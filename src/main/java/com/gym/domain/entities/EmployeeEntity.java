package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "employees")
@Table(name = "employees")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String document;

    @Column
    private EmployeeStatus status;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity user;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;


}
