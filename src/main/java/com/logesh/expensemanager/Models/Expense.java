package com.logesh.expensemanager.Models;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Expense {
    private LocalDate date;
    private String whom;
    private String category;
    private String name;
    private String type;
    private int amount;
}
