package com.springapp.inventoryapi.repository;

import com.springapp.inventoryapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
