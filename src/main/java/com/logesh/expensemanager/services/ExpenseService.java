package com.logesh.expensemanager.services;

import java.util.List;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.UserExpense;

import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    public UserExpense save(Expense expense, String username);

    public UserExpense findOneExpense(String username);

    public List<UserExpense> findByMonth(int month, String username, int year);
}
