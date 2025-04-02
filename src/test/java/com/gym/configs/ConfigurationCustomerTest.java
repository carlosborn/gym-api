package com.gym.configs;

import com.gym.application.services.CustomerAccessApplicationService;
import com.gym.application.services.CustomerApplicationService;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.entities.CustomerGender;
import com.gym.domain.services.CustomerAccessService;
import com.gym.domain.services.CustomerService;
import com.gym.infra.repositories.CustomerAccessRepository;
import com.gym.infra.repositories.CustomerRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@TestConfiguration
public class ConfigurationCustomerTest {

    @Bean
    public CustomerApplicationService customerApplicationServiceMock() {
        CustomerRepository mockRepository = Mockito.mock(CustomerRepository.class);

        Mockito.when(mockRepository.save(new CustomerEntity())).thenReturn(new CustomerEntity());

        Optional<CustomerEntity> customerEntity = Optional.of(new CustomerEntity());
        Mockito.when(mockRepository.findByAccessCode(123456)).thenReturn(customerEntity);

        Optional<CustomerEntity> customerEntityEmpty = Optional.empty();
        Mockito.when(mockRepository.findByAccessCode(654321)).thenReturn(customerEntityEmpty);

        CustomerService customerService = new CustomerService(mockRepository);

        CustomerEntity customer = new CustomerEntity();
        CustomerApplicationService customerApplicationService = new CustomerApplicationService(customerService);
        Mockito.when(customerApplicationService.createCustomer("Test", "Test", CustomerGender.MALE, LocalDate.of(2025, 1, 1), 1.0, 1.0)).thenReturn(customer);

        return customerApplicationService;
    }

}
