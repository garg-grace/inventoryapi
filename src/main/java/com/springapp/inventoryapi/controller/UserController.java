package com.springapp.inventoryapi.controller;

import com.springapp.inventoryapi.model.User;
import com.springapp.inventoryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000/"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User login(Principal principal){
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return user;

    }
}
