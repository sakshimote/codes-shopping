package com.bank.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FixedDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	
	private Long principleAmount;
	private Long period;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	private Long rate;
	private Long fdAmount;
	private Long interest;
	
	
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}
	public Long getFdAmount() {
		return fdAmount;
	}
	public void setFdAmount(Long fdAmount) {
		this.fdAmount = fdAmount;
	}
	public Long getInterest() {
		return interest;
	}
	public void setInterest(Long interest) {
		this.interest = interest;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPrincipleAmount() {
		return principleAmount;
	}
	public void setPrincipleAmount(Long principleAmount) {
		this.principleAmount = principleAmount;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	
	
}
