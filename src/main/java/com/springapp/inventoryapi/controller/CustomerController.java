package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.enums.RoleType;
import com.springapp.inventoryapi.model.Address;
import com.springapp.inventoryapi.model.Customer;
import com.springapp.inventoryapi.model.User;
import com.springapp.inventoryapi.service.AddressService;
import com.springapp.inventoryapi.service.CustomerService;
import com.springapp.inventoryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        User user = customer.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.CUSTOMER);
        user = userService.insert(user);
        customer.setUser(user);

        Address address = customer.getAddress();
        address = addressService.insert(address);
        customer.setAddress(address);

        customer = customerService.insert(customer);

        return ResponseEntity.status(HttpStatus.OK).body(customer);

    }
}
