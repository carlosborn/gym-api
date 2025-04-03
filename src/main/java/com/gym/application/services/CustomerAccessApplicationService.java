package com.gym.application.services;

import com.gym.domain.beans.CustomerAccessStatisticBean;
import com.gym.domain.entities.CustomerAccessEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.exceptions.CustomerAccessNotFoundException;
import com.gym.domain.exceptions.InvalidAccessCodeException;
import com.gym.domain.services.CustomerAccessService;
import com.gym.domain.services.CustomerService;
import com.gym.infra.helpers.DateHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CustomerAccessApplicationService {

    @Autowired
    private CustomerAccessService customerAccessService;

    @Autowired
    private CustomerApplicationService customerApplicationService;

    public Page<CustomerAccessEntity> getByCustomer(Long customerId, Pageable pageable) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        return this.customerAccessService.findByCustomer(customerEntity, pageable);
    }

    public Page<CustomerAccessEntity> getByCustomerAndBetweenDates(Long customerId, Date start, Date end, Pageable pageable) {
        CustomerEntity customerEntity = this.customerApplicationService.getById(customerId);
        return this.customerAccessService.findByCustomerAndCreatedAtBetween(customerEntity, start, end, pageable);
    }

    public boolean validAccessCode(Integer accessCode) {
        if (accessCode <= 0) {
            throw new InvalidAccessCodeException();
        }

        CustomerEntity customerEntity = this.customerApplicationService.getByAccessCode(accessCode);

        CustomerAccessEntity customerAccessEntity = this.createCustomerAccess(customerEntity);

        return customerAccessEntity.getId() != null;
    }

    private CustomerAccessEntity createCustomerAccess(CustomerEntity customerEntity) {
        CustomerAccessEntity customerAccessEntity = new CustomerAccessEntity();
        customerAccessEntity.setCustomer(customerEntity);
        customerAccessEntity.setCreatedAt(new Date());

        return this.customerAccessService.save(customerAccessEntity);
    }

    public CustomerAccessStatisticBean getAccessesStatisticsByCustomerAndRangeDates(Long customerId, Date startDate, Date endDate) {

        CustomerEntity customer = this.customerApplicationService.getById(customerId);

        List<CustomerAccessEntity> accesses = this.customerAccessService
                .findByCustomerAndCreatedAtBetween(customer, startDate, endDate);

        if (accesses.isEmpty()) {
            throw new CustomerAccessNotFoundException();
        }

        int daysAttended = 0;
        List<Date> dates = new ArrayList<>();
        CustomerEntity customerEntity = null;
        for (CustomerAccessEntity customerAccessEntity : accesses) {

            // Valida se a data ja existe
            if (!dates.stream().filter(date -> DateHelper.isSameDate(date, customerAccessEntity.getCreatedAt())).toList().isEmpty()) {
                continue;
            }

            dates.add(customerAccessEntity.getCreatedAt());

            daysAttended++;
            customerEntity = customerAccessEntity.getCustomer();
        }

        return new CustomerAccessStatisticBean(daysAttended, customerEntity, dates.getLast());
    }

}
