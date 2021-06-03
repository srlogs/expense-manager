package com.logesh.expensemanager.services;

import java.util.Optional;

import com.logesh.expensemanager.Models.User;
import com.logesh.expensemanager.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        User userData = new User();
        userData.setUsername(user.getUsername());
        userData.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(userData);
    }

    @Override
    public String authenticate(User user) {
        Optional<User> optional = repository.findByUsername(user.getUsername());
        if (optional.isEmpty()) {
            return "User not found";
        } else if (validate(user, optional.get())) {
            return "authenticated";
        }
        return "failed";
    }

    private boolean validate(User credentials, User userData) {
        if (!passwordEncoder.matches(credentials.getPassword(), userData.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public User findOneUser(String username) {
        Optional<User> optional = repository.findByUsername(username);
        if (optional.isEmpty()) {
            return null;
        }
        return optional.get();
    }

}
