package com.logesh.expensemanager.Repositories;

import com.logesh.expensemanager.Models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
