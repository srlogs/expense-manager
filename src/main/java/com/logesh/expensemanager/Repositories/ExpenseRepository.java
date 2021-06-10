package com.logesh.expensemanager.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.ExpenseYears;
import com.logesh.expensemanager.Models.UserExpense;
import com.mongodb.client.result.UpdateResult;

public interface ExpenseRepository {
    Optional<UserExpense> findByUserId(String userId);

    UserExpense save(UserExpense userExpense);

    UpdateResult update(Expense expense, String userId);

    List<UserExpense> findByMonth(int month, String userId, int year);

    List<ExpenseYears> findYears(String userId, int month);

    void delete(String userId, Date createdDate);

    UpdateResult updateExpense(Expense expense, String userId);
}
