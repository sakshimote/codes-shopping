package com.bank.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loan_id;
	
	@Column(nullable = false)
	private Long loanAmount;
	
	private int loanTerm;
	
	private Long rate;

	
	private Long monthlyPayment;
	

	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}

	public Long getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(Long monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	
	public Long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}
	

}
