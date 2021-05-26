package com.logesh.expensemanager.Repositories;

import java.util.Optional;

import com.logesh.expensemanager.Models.UserExpense;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<UserExpense, String> {
    Optional<UserExpense> findByUserId(String userId);
}
