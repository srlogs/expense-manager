package com.logesh.expensemanager.controllers;

import com.logesh.expensemanager.Models.User;
import com.logesh.expensemanager.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return service.save(user);
    }
}
