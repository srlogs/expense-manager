package com.logesh.expensemanager.controllers;

import java.util.Date;
import java.util.List;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.ExpenseYears;
import com.logesh.expensemanager.Models.UserExpense;
import com.logesh.expensemanager.services.ExpenseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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
    public UserExpense getExpenseByMonth(@PathVariable("username") String username, @PathVariable("month") int month,
            @PathVariable("year") int year) {
        if (service.findByMonth(month, username, year).size() == 0) {
            return new UserExpense();
        }
        return service.findByMonth(month, username, year).get(0);
    }

    @GetMapping("/years/{username}/{month}")
    public List<ExpenseYears> getExpenseYears(@PathVariable("username") String username,
            @PathVariable("month") int month) {
        return service.findYears(username, month);
    }

    @DeleteMapping("/{username}/{createdDate}")
    public void deleteExpense(@PathVariable("username") String username,
            @PathVariable("createdDate") @DateTimeFormat(iso = ISO.DATE_TIME) Date createdDate) {
        service.delete(username, createdDate);
    }

    @PostMapping("/update/{username}")
    public String updateExpense(@PathVariable("username") String username, @RequestBody Expense expense) {
        return service.updateExpense(username, expense);
    }
}
