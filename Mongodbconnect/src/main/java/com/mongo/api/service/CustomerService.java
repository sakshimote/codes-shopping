package com.mongo.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.api.model.Customer;
import com.mongo.api.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	
	public Customer saveCustomer(Customer c) {
		return repo.save(c);
	}
	
	public List<Customer> saveListCustomers(List<Customer> list){
		return repo.saveAll(list);
	}
	
	public List<Customer> getAllCustomers(){
		return repo.findAll();
	}
	
	public Optional<Customer> getCustomerById(String id){
		return repo.findById(id);
	}
	
	public Customer updateCustomer(String id,Customer c) {
		Optional<Customer> customerop=repo.findById(id);
		Customer customer=customerop.get();
		
		customer.setFirstName(c.getFirstName());
		customer.setLastName(c.getLastName());
		customer.setId(c.getId());
		
		return repo.save(customer);
		
	}
	
	public void deleteCustomerById(String id) {
		repo.deleteById(id);
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
	

}
