package com.logesh.expensemanager.services;

import com.logesh.expensemanager.Models.User;
import com.logesh.expensemanager.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
