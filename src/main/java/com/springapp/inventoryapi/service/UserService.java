package com.springapp.inventoryapi.service;

import com.springapp.inventoryapi.model.User;
import com.springapp.inventoryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User insert(User user) {
        return userRepository.save(user);
    }
}
