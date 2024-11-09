package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Customer;
import com.springapp.inventoryapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }
}
