package com.logesh.expensemanager.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Expense {
    private String date;
    private String whom;
    private String category;
    private String name;
    private String type;
    private int amount;
}
