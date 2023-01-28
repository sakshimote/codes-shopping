package com.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.api.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	

}
