package com.logesh.expensemanager.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "expenses")
public class UserExpense {

    @Id
    private String id;
    private String userId;
    private List<Expense> expense;
}
