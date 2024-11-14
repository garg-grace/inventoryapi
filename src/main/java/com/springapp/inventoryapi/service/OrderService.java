package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Order;
import com.springapp.inventoryapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order insert(Order order) {
        return orderRepository.save(order);
    }
}
