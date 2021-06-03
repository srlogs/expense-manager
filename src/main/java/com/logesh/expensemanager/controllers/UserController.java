package com.logesh.expensemanager.controllers;

import com.logesh.expensemanager.Models.User;
import com.logesh.expensemanager.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl service;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return service.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return service.authenticate(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return service.findOneUser(username);
    }
}
