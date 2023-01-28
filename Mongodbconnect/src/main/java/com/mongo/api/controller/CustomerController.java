package com.mongo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.api.model.Customer;
import com.mongo.api.repository.CustomerRepository;
import com.mongo.api.service.CustomerService;

@RestController

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository repo;
	
	
	@GetMapping("/users")
	public List<Customer> customerList(){
		return customerService.getAllCustomers();
	}
	
	
	@PostMapping("/user")
	public Customer postCustomer(@RequestBody Customer c) {
		return customerService.saveCustomer(c);
	}

}
