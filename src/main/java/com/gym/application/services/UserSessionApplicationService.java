package com.gym.application.services;

import com.gym.domain.entities.UserEntity;
import com.gym.domain.entities.UserSessionEntity;
import com.gym.domain.services.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserSessionApplicationService {

    @Autowired
    private UserSessionService userSessionService;

    public UserSessionEntity createUserSession(UserEntity userEntity) {
        UserSessionEntity userSessionEntity = new UserSessionEntity();
        userSessionEntity.setUser(userEntity);
        userSessionEntity.setCreatedAt(new Date());
        return this.userSessionService.save(userSessionEntity);
    }

    public Page<UserSessionEntity> getByUser(UserEntity userEntity, Pageable pageable) {
        return this.userSessionService.findByUser(userEntity, pageable);
    }

}
