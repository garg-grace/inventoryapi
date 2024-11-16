package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.Order;
import com.springapp.inventoryapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAll(Pageable pageable, int id) {

        List<Order> list = orderRepository.findBySupplierId(id,pageable);
//        list = list.parallelStream()
//                .filter(order -> order.getSupplier().getUser().getUsername().equalsIgnoreCase(supplierUsername))
//                .collect(Collectors.toList());
        return list;
    }
}
