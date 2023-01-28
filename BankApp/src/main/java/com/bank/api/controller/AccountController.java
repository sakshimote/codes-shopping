package com.bank.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.dto.AccountDto;
import com.bank.api.model.Account;
import com.bank.api.model.Log;
import com.bank.api.model.User;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.LogRepository;
import com.bank.api.repository.UserRepository;
import com.bank.api.validation.AccountValidator;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})

public class AccountController {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LogRepository logRepository;
	
	
	private AccountValidator accountValidator;
	
	@PostMapping("/account/post/{uid}")
	public Account postAccount(@RequestBody Account account,@PathVariable("uid")Long uid) {
		accountValidator=new AccountValidator();
		User user=userRepository.getById(uid);
		account.setOwner(user);
		if(!accountValidator.accountValidate(account)){
			return null;	
		}
		Log log=new Log();
		log.setOperation(user.getName()+" filled information related to account details");
		log.setTimestamp(LocalDate.now());
		log.setUser(user);
		logRepository.save(log);
		return accountRepository.save(account);
		
	}
	@GetMapping("/account/get/{uid}")
	public List<Account> getAccountsByUser(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Account> accounts=accountRepository.findAll();
		List<Account> accountsByUserId=accounts.stream().filter(a->a.getOwner()
				.getUserId()==user.getUserId()).collect(Collectors.toList());
		return accountsByUserId;
	}
	
	@GetMapping("/account/balance/{uid}")
	public AccountDto getAccountDetails(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Account> accounts=accountRepository.findAll();
		List<Account> accountsByUserId=accounts.stream().filter(a->a.getOwner()
				.getUserId()==user.getUserId()).collect(Collectors.toList());
		AccountDto dto=new AccountDto();
		for(Account a:accountsByUserId) {
			dto.setBalance(a.getBalance());
			dto.setName(a.getOwner().getName());
		}
		return dto;
		
	}
	
	
	
	

}
