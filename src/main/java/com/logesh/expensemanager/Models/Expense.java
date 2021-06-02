package com.logesh.expensemanager.Models;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Expense {
    @DateTimeFormat(iso = DATE)
    private Date createdDate;
    private String whom;
    private String category;
    private String name;
    private String type;
    private int amount;
}
