package com.gym.application.config;

import com.gym.application.dtos.UserSessionODTO;
import com.gym.application.mappers.DefaultModelMapper;
import com.gym.domain.entities.UserSessionEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new DefaultModelMapper();
    }

}
