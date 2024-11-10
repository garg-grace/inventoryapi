package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Address;
import com.springapp.inventoryapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    public Address insert(Address address) {
        return addressRepository.save(address);
    }
}
