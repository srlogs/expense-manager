package com.logesh.expensemanager.services;

import com.logesh.expensemanager.Models.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User save(User user);

    public User authenticate(User user);

    public User findOneUser(String username);
}
