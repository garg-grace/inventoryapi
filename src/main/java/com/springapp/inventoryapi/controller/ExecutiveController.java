package com.springapp.inventoryapi.controller;


import com.springapp.inventoryapi.enums.RoleType;
import com.springapp.inventoryapi.model.*;
import com.springapp.inventoryapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/executive")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/supplier/add")
    public Supplier postSupplier(@RequestBody Supplier supplier){
        //Step1:detach address ,save the address and attach the saved object back to supplier
        Address address = supplier.getAddress();
        address = addressService.insert(address);
        supplier.setAddress(address);

        //Step2:detach user, encode the password, set the role , then save user, reattach to supplier
        User user = supplier.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.SUPPLIER);
        user = userService.insert(user);
        supplier.setUser(user);

        //Step3:save supplier
        supplier = supplierService.insert(supplier);
        return supplier;
    }

    @PostMapping("/add")
    public Executive postExecutive(@RequestBody Executive executive){
        //step1:detach user, encode the password, set the role , then save user, reattach to executive
        User user = executive.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.EXECUTIVE);
        user = userService.insert(user);
        executive.setUser(user);

        executive = executiveService.insert(executive);
        return executive;
    }

    @GetMapping("/order/all/{supplierId}")
    public List<Order> getAll(@RequestParam("page") Integer page,
                              @RequestParam("size") Integer size,
                              @PathVariable("supplierId") int supplierId){
        Pageable pageable = PageRequest.of(page,size);

        Supplier supplier = supplierService.getById(supplierId);
        List<Order> list = orderService.getAll(pageable,supplier.getId());
        return list;
    }

}
