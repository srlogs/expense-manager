package com.logesh.expensemanager.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.logesh.expensemanager.Models.Expense;
import com.logesh.expensemanager.Models.ExpenseYears;
import com.logesh.expensemanager.Models.UserExpense;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
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

    @Override
    public List<UserExpense> findByMonth(int month, String userId, int year) {

        AggregationOperation userIdMatch = Aggregation.match(Criteria.where("userId").is(userId));
        AggregationOperation unwind = Aggregation.unwind("expense");
        AggregationOperation project = Aggregation.project().and("userId").as("userId").and("expense").as("expense")
                .andExpression("month(expense.createdDate)").as("month").andExpression("year(expense.createdDate)")
                .as("year");
        AggregationOperation monthMatch = Aggregation
                .match(new Criteria().andOperator(Criteria.where("month").is(month), Criteria.where("year").is(year)));
        AggregationOperation group = Aggregation.group("userId").first("userId").as("userId").push("expense")
                .as("expense");
        AggregationOperation finalProject = Aggregation.project().and("userId").as("userId").and("expense")
                .as("expense");

        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(userIdMatch);
        operations.add(unwind);
        operations.add(project);
        operations.add(monthMatch);
        operations.add(group);
        operations.add(finalProject);
        Aggregation aggregation = Aggregation.newAggregation(operations);

        return mongoTemplate.aggregate(aggregation, UserExpense.class, UserExpense.class).getMappedResults();
    }

    @Override
    public List<ExpenseYears> findYears(String userId, int month) {
        AggregationOperation userIdMatch = Aggregation.match(Criteria.where("userId").is(userId));
        AggregationOperation unwind = Aggregation.unwind("expense");
        AggregationOperation project = Aggregation.project().andExpression("year(expense.createdDate)").as("year")
                .andExpression("month(expense.createdDate)").as("month");
        AggregationOperation monthMatch = Aggregation.match(Criteria.where("month").is(month));
        AggregationOperation finalProject = Aggregation.project().and("year").as("year");
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(userIdMatch);
        operations.add(unwind);
        operations.add(project);
        operations.add(monthMatch);
        operations.add(finalProject);

        Aggregation aggregation = Aggregation.newAggregation(operations);
        return mongoTemplate.aggregate(aggregation, UserExpense.class, ExpenseYears.class).getMappedResults();
    }

}
