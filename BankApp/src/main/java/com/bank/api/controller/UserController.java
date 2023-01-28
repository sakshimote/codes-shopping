package com.bank.api.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Log;
import com.bank.api.model.User;
import com.bank.api.repository.LogRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})

public class UserController {
	
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LogRepository logRepository;
	
	@PostMapping("/user/register")
	public User postUser(@RequestBody User user) {
		 
		

		Log log=new Log();
		log.setOperation(user.getName()+" registered to AXC Bank");
		log.setTimestamp(LocalDate.now());
		log.setUser(user);
		logRepository.save(log);
		
		return userRepository.save(user);	
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	

}
