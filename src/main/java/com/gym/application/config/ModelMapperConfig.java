package com.gym.application.config;

import com.gym.application.dtos.UserSessionODTO;
import com.gym.domain.entities.UserSessionEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<UserSessionEntity, UserSessionODTO>() {
            protected void configure() {
                map(source.getUser(), destination.getUserODTO());
            }
        });

        return modelMapper;
    }

}
