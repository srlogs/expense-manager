package com.logesh.expensemanager;

import com.logesh.expensemanager.services.ExpenseServiceImpl;
import com.logesh.expensemanager.services.UserServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerApplication.class, args);
	}

	@Bean
	public UserServiceImpl userService() {
		return new UserServiceImpl();
	}

	@Bean
	public ExpenseServiceImpl expenseService() {
		return new ExpenseServiceImpl();
	}

}
