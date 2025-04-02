package com.gym.configs;

import com.gym.application.services.CustomerAccessApplicationService;
import com.gym.application.services.CustomerApplicationService;
import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.services.CustomerAccessService;
import com.gym.infra.repositories.CustomerAccessRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ConfigurationCustomerAccessTest {

    @Autowired
    private CustomerApplicationService customerApplicationServiceMock;

    @Bean
    public CustomerAccessApplicationService customerAccessApplicationServiceMock() {
        CustomerAccessRepository mockRepository = Mockito.mock(CustomerAccessRepository.class);
        CustomerAccessService customerAccessService = new CustomerAccessService(mockRepository);

        CustomerAccessEntity customerAccessEntity = new CustomerAccessEntity();
        customerAccessEntity.setId(1L);
        Mockito.when(customerAccessService.save(null)).thenReturn(customerAccessEntity);

        return new CustomerAccessApplicationService(customerAccessService, this.customerApplicationServiceMock);
    }

}
