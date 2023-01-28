package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Loan;
import com.bank.api.model.User;
import com.bank.api.repository.LoanRepository;
import com.bank.api.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})

public class LoanController {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
		
		
		

	@GetMapping("/loan/{uid}")
	public List<Loan> getLoanByUid(@PathVariable("uid")Long uid) {
		List<Loan> loans=loanRepository.findByUserUserId(uid);
		return loans;
	}
	
	@PostMapping("/post/loan/{uid}")
	public Loan postLoan(@PathVariable("uid")Long uid,@RequestBody Loan loan) {
		User user=userRepository.getById(uid);
		loan.setUser(user);
		
		
		int termsInMonths=loan.getLoanTerm()*12;
		loan.setLoanTerm(termsInMonths);
		

		long monthlyPayment=(loan.getLoanAmount()*loan.getRate()/100*loan.getLoanTerm())/100;
		loan.setMonthlyPayment(monthlyPayment);
		return loanRepository.save(loan);
	}
}
