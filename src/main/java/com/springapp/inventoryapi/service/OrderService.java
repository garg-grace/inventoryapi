package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.dto.MessageDto;
import com.springapp.inventoryapi.enums.OrderStatus;
import com.springapp.inventoryapi.model.InwardRegister;
import com.springapp.inventoryapi.model.Order;
import com.springapp.inventoryapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InwardRegisterService inwardRegisterService;

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

    public ResponseEntity<?> updateOrderStatus(int oid, String status) {
        OrderStatus ostatus = OrderStatus.valueOf(status);
        Order order = orderRepository.findById(oid).get();

        switch (ostatus){
            case ACCEPTED:
                order.setOrderStatus(OrderStatus.ACCEPTED);
                orderRepository.save(order);
                break;
            case REJECTED:
                order.setOrderStatus(OrderStatus.REJECTED);
                orderRepository.save(order);
                break;
            case DELIVERED:
                InwardRegister entry = inwardRegisterService.insert(order);
                order.setOrderStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
                return ResponseEntity.status(HttpStatus.OK).body(entry);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDto("Status updated.."));
    }
}
