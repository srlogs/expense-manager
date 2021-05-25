package com.logesh.expensemanager.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Document(collection = User.USERS)
public class User {

    static final String USERS = "users";
    @Id
    private String id;
    private String username;
    private String password;
}
