package com.gym.domain.services;

import com.gym.domain.entities.AddressEntity;
import com.gym.infra.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressEntity save(AddressEntity addressEntity) {
        return this.addressRepository.save(addressEntity);
    }

    public Page<AddressEntity> findAll(Pageable pageable) {
        return this.addressRepository.findAll(pageable);
    }

}
