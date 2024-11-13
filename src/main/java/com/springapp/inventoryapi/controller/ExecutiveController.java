package com.springapp.inventoryapi.controller;


import com.springapp.inventoryapi.enums.RoleType;
import com.springapp.inventoryapi.model.Address;
import com.springapp.inventoryapi.model.Supplier;
import com.springapp.inventoryapi.model.User;
import com.springapp.inventoryapi.service.AddressService;
import com.springapp.inventoryapi.service.ExecutiveService;
import com.springapp.inventoryapi.service.SupplierService;
import com.springapp.inventoryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
