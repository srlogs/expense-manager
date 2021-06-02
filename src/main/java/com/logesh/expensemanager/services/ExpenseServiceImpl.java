package com.logesh.expensemanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.User;
import com.logesh.expensemanager.Models.UserExpense;

import com.logesh.expensemanager.Repositories.ExpenseRepository;
import com.logesh.expensemanager.Repositories.UserRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository repository;

    @Autowired
    UserRepository userRepository;

    private UpdateResult update;
    private String userId;

    @Override
    public UserExpense save(Expense expense, String username) {
        userId = getUserId(username);
        Optional<UserExpense> optional = repository.findByUserId(userId);
        if (optional.isPresent()) {
            optional.get().getExpense().add(expense);
            update = repository.update(expense, userId);
            return optional.get();
        }
        UserExpense userExpense = new UserExpense();
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(expense);
        userExpense.setUserId(userId);
        userExpense.setExpense(expenseList);
        return repository.save(userExpense);
    }

    @Override
    public UserExpense findOneExpense(String username) {
        userId = getUserId(username);
        Optional<UserExpense> optional = repository.findByUserId(userId);
        return optional.get();
    }

    @Override
    public List<UserExpense> findByMonth(int month, String username) {
        userId = getUserId(username);
        return repository.findByMonth(month, userId);
    }

    private String getUserId(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.get().getId();
    }

}
