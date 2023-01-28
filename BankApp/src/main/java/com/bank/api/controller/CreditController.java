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
import com.bank.api.model.Credit;
import com.bank.api.model.User;
import com.bank.api.model.Withdraw;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.CreditRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CreditController {
	@Autowired
	private CreditRepository creditRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	
	@PostMapping("/credit/{uid}")
	public Credit credit(@PathVariable("uid")Long uid,@RequestBody Credit credit) {
		User user=userRepository.getById(uid);
		credit.setUser(user);
		List<Account> accounts=accountRepository.findAll();
		List<Account> accountsByUserId=accounts.stream().filter(a->a.getOwner()
				.getUserId()==user.getUserId()).collect(Collectors.toList());
		for(Account a:accountsByUserId) {
			Long balance=a.getBalance()+credit.getAmount();
			credit.setBalance(balance);
			a.setBalance(balance);
		}
		return creditRepository.save(credit);
		}
	@GetMapping("/credit/get/{uid}")
	public List<Credit> getCreditDataByUserId(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Credit> creditData=creditRepository.findAll();
		List<Credit> creditByUserId=creditData.stream()
				.filter(c->c.getUser().getUserId()==user.getUserId()).collect(Collectors.toList());
		return creditByUserId;
		
	}
	

}
