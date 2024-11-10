package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.model.User;
import com.springapp.inventoryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(Principal principal){
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return user;

    }
}
