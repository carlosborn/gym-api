package com.gym.application.mappers;

import com.gym.application.dtos.*;
import com.gym.domain.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class DefaultModelMapper extends ModelMapper {

    public DefaultModelMapper() {
        super();
        this.initPropertiesMappers();
    }

    private void initPropertiesMappers() {
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
        this.addMappings(new PropertyMap<EnrollmentEntity, EnrollmentODTO>() {
            protected void configure() {
                map(source.getCustomer(), destination.getCustomer());
                map(source.getMembershipPlan(), destination.getMembershipPlan());
            }
        });
        this.addMappings(new PropertyMap<CustomerAccessEntity, CustomerAccessODTO>() {
            protected void configure() {
                map(source.getCustomer(), destination.getCustomer());
            }
        });
    }

}
