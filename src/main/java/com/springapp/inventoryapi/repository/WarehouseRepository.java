package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
}
