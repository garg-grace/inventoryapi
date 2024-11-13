package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
