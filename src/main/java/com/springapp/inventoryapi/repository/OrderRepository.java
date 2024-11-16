package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findBySupplierId(int id, Pageable pageable);
}
