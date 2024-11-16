package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Query("select s from Supplier s where s.user.username=?1")
    Supplier getByUsername(String supplierUsername);
}
