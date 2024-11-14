package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Warehouse;
import com.springapp.inventoryapi.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse getById(int warehouseId) {
        return warehouseRepository.findById(warehouseId).get();
    }
}
