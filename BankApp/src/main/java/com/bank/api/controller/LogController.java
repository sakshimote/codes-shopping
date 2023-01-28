package com.bank.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Log;
import com.bank.api.model.User;
import com.bank.api.repository.LogRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})

public class LogController {
	@Autowired
	private LogRepository logRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/logs/{uid}")
	public List<Log> getLogsByUid(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Log> logs=logRepository.findAll();
		List<Log> logByUserId=logs.stream().filter(l->l.getUser().getUserId()==user.getUserId()).collect(Collectors.toList());
		return logByUserId;
	}
	

}
