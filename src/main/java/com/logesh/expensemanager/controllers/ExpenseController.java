package com.logesh.expensemanager.controllers;

import java.util.List;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.UserExpense;
import com.logesh.expensemanager.services.ExpenseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseServiceImpl service;

    @PostMapping("/{username}")
    public UserExpense saveExpense(@RequestBody Expense expense, @PathVariable("username") String username) {
        return service.save(expense, username);
    }

    @GetMapping("/{username}")
    public UserExpense getExpense(@PathVariable("username") String username) {
        return service.findOneExpense(username);
    }

    @GetMapping("/{username}/{month}/{year}")
    public List<UserExpense> getExpenseByMonth(@PathVariable("username") String username,
            @PathVariable("month") int month, @PathVariable("year") int year) {
        return service.findByMonth(month, username, year);
    }
}
