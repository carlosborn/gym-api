package com.gym;

import com.gym.application.services.CustomerAccessApplicationService;
import com.gym.domain.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerAccessTests {

    @Autowired
    private CustomerAccessApplicationService customerAccessApplicationServiceMock;

    @Test
    public void testValidationAccessCode() {
        Assertions.assertTrue(this.customerAccessApplicationServiceMock.validAccessCode(123456));
        Assertions.assertThrows(CustomerNotFoundException.class, () -> this.customerAccessApplicationServiceMock.validAccessCode(654321));
    }

}
