package com.gym.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "users_sessions")
@Table(name = "users_sessions")
@Getter
@Setter
public class UserSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, targetEntity = UserEntity.class)
    private UserEntity user;

    @Column(name = "created_at")
    private Date createdAt;

}
