package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.enums.OrderStatus;
import com.springapp.inventoryapi.model.InwardRegister;
import com.springapp.inventoryapi.model.Order;
import com.springapp.inventoryapi.repository.InwardRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InwardRegisterService {

    @Autowired
    private InwardRegisterRepository inwardRegisterRepository;

    public InwardRegister insert(Order order) {
        InwardRegister ir = new InwardRegister();
        ir.setInvoice("IV-"+order.getSupplier().getId()+"-"+(int)(Math.random()*1000));
        ir.setQuantity(order.getQuantity());
        ir.setDateOfSupply(LocalDate.now());
        ir.setOrderStatus(OrderStatus.RECEIVED);
        ir.setProduct(order.getProduct());
        ir.setSupplier(order.getSupplier());
        ir.setWarehouse(order.getWarehouse());
        return inwardRegisterRepository.save(ir);
    }
}
