package com.bank.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Account;
import com.bank.api.model.Transfer;
import com.bank.api.model.User;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.TransferRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class TransferController {
	@Autowired
	private TransferRepository transferRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/transferTo/{uid}/{transferToUsername}")
	public Transfer transferTo(@PathVariable("uid")Long uid,@PathVariable("transferToUsername")
	           String transferToUsername,
			@RequestBody Transfer transfer) {
		User userfrom=userRepository.getById(uid);
		User userTo=userRepository.findByUsername(transferToUsername);
		transfer.setTransferFromUsername(userfrom.getUsername());
		transfer.setTransferToUsername(userTo.getUsername());
		List<Account> accounts=accountRepository.findAll();
		List<Account> accountByUserFrom=accounts.stream()
		  .filter(a->a.getOwner().getUserId()==userfrom.getUserId()).collect(Collectors.toList());
		
		for(Account a:accountByUserFrom) {
		if(a.getBalance()>transfer.getAmount()) {
			long balance=a.getBalance()-transfer.getAmount();
			a.setBalance(balance);
			accountRepository.save(a);
		}
			
		}
		
		List<Account> accountByUserTo=accounts.stream()
				.filter(a->a.getOwner().getUsername().equalsIgnoreCase(userTo.getUsername()))
				.collect(Collectors.toList());
		
		for(Account a:accountByUserTo) {
			long balance=a.getBalance()+transfer.getAmount();
			a.setBalance(balance);
			accountRepository.save(a);
		}
		
		return transferRepository.save(transfer);
	}
	@GetMapping("/get/transfer")
	public List<Transfer> getTransferDetails() {
		return transferRepository.findAll();
	}
	
	

}
