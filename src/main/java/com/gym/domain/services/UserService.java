package com.gym.domain.services;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserStatus;
import com.gym.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public Optional<UserEntity> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    public Page<UserEntity> findAll(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public Page<UserEntity> findAllByStatus(UserStatus userStatus, Pageable pageable) {
        return this.userRepository.findAllByStatus(userStatus, pageable);
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> optional = userRepository.findByUsername(username);

        if (optional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserEntity userEntity = optional.get();

        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
