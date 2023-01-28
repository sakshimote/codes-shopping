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
import com.bank.api.model.User;
import com.bank.api.model.Withdraw;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.UserRepository;
import com.bank.api.repository.WithdrawRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class WithdrawController {
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/withdraw/{uid}")
	public Withdraw Withdraw(@PathVariable("uid")Long uid,@RequestBody Withdraw withdraw) {
		User user=userRepository.getById(uid);
		withdraw.setUser(user);
		List<Account> accounts=accountRepository.findAll();
		List<Account> accountsByUserId=accounts.stream().filter(a->a.getOwner()
				.getUserId()==user.getUserId()).collect(Collectors.toList());
		for(Account a:accountsByUserId) {
			if(a.getBalance()>=withdraw.getAmount()) {
			Long balance=a.getBalance()-withdraw.getAmount();
			withdraw.setBalance(balance);
			a.setBalance(balance);
			}
		}
		return withdrawRepository.save(withdraw);	
	}
	
	@GetMapping("/withdraw/get/{uid}")
	public List<Withdraw> getWithdrawDetailsByUserId(@PathVariable("uid")Long uid) {
		User user=userRepository.getById(uid);
		List<Withdraw> withdrawData=withdrawRepository.findAll();
		List<Withdraw> withdrawByUserId=withdrawData.stream()
				.filter(w->w.getUser().getUserId()==user.getUserId()).collect(Collectors.toList());
		return withdrawByUserId;
	}
	

}
