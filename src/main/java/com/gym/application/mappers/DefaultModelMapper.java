package com.gym.application.mappers;

import com.gym.application.dtos.AddressODTO;
import com.gym.application.dtos.CustomerODTO;
import com.gym.application.dtos.UserSessionODTO;
import com.gym.domain.entities.AddressEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.UserSessionEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class DefaultModelMapper extends ModelMapper {

    public DefaultModelMapper(){
        super();
        this.initPropertiesMappers();
    }

    private void initPropertiesMappers(){
        this.addMappings(new PropertyMap<UserSessionEntity, UserSessionODTO>() {
            protected void configure() {
                map(source.getUser(), destination.getUserODTO());
            }
        });
        this.addMappings(new PropertyMap<CustomerEntity, CustomerODTO>() {
            protected void configure() {
                map(source.getAddress(), destination.getAddress());
            }
        });
    }

}
