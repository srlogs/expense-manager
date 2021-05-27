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

    @Override
    public UserExpense save(Expense expense, String username) {
        Optional<User> optional = userRepository.findByUsername(username);

        if (optional.isPresent()) {
            Optional<UserExpense> expenseOptional = repository.findByUserId(optional.get().getId());
            if (expenseOptional.isPresent()) {
                expenseOptional.get().getExpense().add(expense);
                update = repository.update(expense, optional.get().getId());
                return expenseOptional.get();
            } else {
                UserExpense userExpense = new UserExpense();
                List<Expense> expenseList = new ArrayList<>();
                expenseList.add(expense);
                userExpense.setUserId(optional.get().getId());
                userExpense.setExpense(expenseList);
                return repository.save(userExpense);
            }
        }
        return null;
    }

    @Override
    public UserExpense findOneExpense(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        Optional<UserExpense> eOptional = repository.findByUserId(optional.get().getId());
        return eOptional.get();
    }

}
