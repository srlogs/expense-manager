package com.logesh.expensemanager.services;

import java.util.Date;
import java.util.List;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.ExpenseYears;
import com.logesh.expensemanager.Models.UserExpense;

import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    public UserExpense save(Expense expense, String username);

    public UserExpense findOneExpense(String username);

    public List<UserExpense> findByMonth(int month, String username, int year);

    public List<ExpenseYears> findYears(String username, int month);

    public void delete(String username, Date createdDate);

    public String updateExpense(String username, Expense expense);
}
