package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.FixedDeposit;
import com.bank.api.model.User;
import com.bank.api.repository.FixedDepositRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})

public class FixedDepositController {
	@Autowired
	private FixedDepositRepository fixedDepositRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("/post/fd/{uid}")
	public FixedDeposit postFD(@PathVariable("uid")Long uid,@RequestBody FixedDeposit fd) {
	
	
		long emi=(fd.getPrincipleAmount()*fd.getRate()*fd.getPeriod())/100;
		fd.setInterest(emi);
		
		
		long fdAmount=fd.getInterest()+fd.getPrincipleAmount();
		fd.setFdAmount(fdAmount);
		
		User user=userRepository.getById(uid);
		fd.setUser(user);
	
	   return fixedDepositRepository.save(fd);
		
	}
	@GetMapping("/get/fd/{uid}")
	public List<FixedDeposit> getFd(@PathVariable("uid")Long uid) {
	
		List<FixedDeposit> fds=fixedDepositRepository.findByUserUserId(uid);
		return fds;
		
		
	}

}
