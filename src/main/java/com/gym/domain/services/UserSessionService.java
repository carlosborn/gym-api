package com.gym.domain.services;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserSessionEntity;
import com.gym.infra.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    public UserSessionEntity save(UserSessionEntity userSessionEntity) {
        return this.userSessionRepository.save(userSessionEntity);
    }

    public Page<UserSessionEntity> findByUser(UserEntity userEntity, Pageable pageable) {
        return this.userSessionRepository.findByUser(userEntity, pageable);
    }
}
