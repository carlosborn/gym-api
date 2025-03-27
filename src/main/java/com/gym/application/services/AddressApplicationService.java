package com.gym.application.services;

import com.gym.domain.entities.AddressEntity;
import com.gym.domain.entities.CustomerEntity;
import com.gym.domain.exceptions.AddressNotFoundException;
import com.gym.domain.services.AddressService;
import com.gym.infra.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressApplicationService {

    @Autowired
    private AddressService addressService;

    public AddressEntity createAddress(String street, String number, String complement, String neighborhood, String city,
                                       String state, String zipcode, CustomerEntity customerEntity) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(street);
        addressEntity.setNumber(number);
        addressEntity.setComplement(complement);
        addressEntity.setNeighborhood(neighborhood);
        addressEntity.setCity(city);
        addressEntity.setState(state);
        addressEntity.setZipcode(zipcode);
        addressEntity.setCustomer(customerEntity);

        return this.addressService.save(addressEntity);
    }

    public AddressEntity updateAddress(Long id, String street, String number, String complement, String neighborhood, String city,
                                       String state, String zipcode) {

        AddressEntity addressEntity = this.getById(id);
        addressEntity.setStreet(street);
        addressEntity.setNumber(number);
        addressEntity.setComplement(complement);
        addressEntity.setNeighborhood(neighborhood);
        addressEntity.setCity(city);
        addressEntity.setState(state);
        addressEntity.setZipcode(zipcode);

        return this.addressService.save(addressEntity);
    }

    public AddressEntity getById(Long id) {
        Optional<AddressEntity> optional = this.addressService.findById(id);

        if (optional.isEmpty()) {
            throw new AddressNotFoundException();
        }

        return optional.get();
    }

}
