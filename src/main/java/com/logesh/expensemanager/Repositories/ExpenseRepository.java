package com.logesh.expensemanager.Repositories;

import java.util.Optional;

import com.logesh.expensemanager.Models.UserExpense;

public interface ExpenseRepository {
    Optional<UserExpense> findByUserId(String userId);

    UserExpense save(UserExpense userExpense);
}
