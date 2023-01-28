package com.mongo.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongo.api.model.Customer;
import com.mongo.api.repository.CustomerRepository;

@SpringBootApplication
public class MongodbconnectApplication  {
	
	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbconnectApplication.class, args);
	}

	
}
