package com.gym.application.services;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserStatus;
import com.gym.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService {

    @Autowired
    private UserService userService;

    public UserEntity createUser(String name, String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());

        return this.userService.save(userEntity);
    }

    public UserEntity updateUser(Long id, String name, String password, UserStatus userStatus) {
        UserEntity userEntity = this.getUserById(id);
        userEntity.setName(name);
        userEntity.setStatus(userStatus);
        userEntity.setUpdatedAt(new Date());

        if (password != null && !password.trim().isEmpty()) {
            userEntity.setPassword(password);
        }

        return this.userService.save(userEntity);
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> optional = this.userService.findById(id);

        if (optional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optional.get();
    }

    public Page<UserEntity> getAllByStatus(UserStatus userStatus, Pageable pageable) {
        return this.userService.findAllByStatus(userStatus, pageable);
    }

    public Page<UserEntity> getAll(Pageable pageable) {
        return this.userService.findAll(pageable);
    }

    public UserEntity getByUsername(String username) {
        Optional<UserEntity> optional = this.userService.findByUsername(username);

        if (optional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optional.get();
    }

}
