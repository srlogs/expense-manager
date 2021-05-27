package com.logesh.expensemanager.Repositories;

import java.util.Optional;

import com.logesh.expensemanager.Models.UserExpense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

}
