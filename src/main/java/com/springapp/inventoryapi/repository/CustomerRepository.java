package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("select c from Customer c where c.user.username=?1")
    Customer getCustomerByUsername(String username);
}
