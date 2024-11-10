package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
