package com.logesh.expensemanager.Repositories;

import java.util.Optional;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.UserExpense;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ExpenseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<UserExpense> findByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return Optional.ofNullable(mongoTemplate.findOne(query, UserExpense.class));
    }

    @Override
    public UserExpense save(UserExpense userExpense) {
        return mongoTemplate.save(userExpense);
    }

    @Override
    public UpdateResult update(Expense expense, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Update update = new Update().push("expense", expense);
        return mongoTemplate.updateFirst(query, update, UserExpense.class);
    }

}
