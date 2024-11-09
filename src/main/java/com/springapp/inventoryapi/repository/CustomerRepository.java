package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
