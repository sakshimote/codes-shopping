package com.bank.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String transferToUsername;
	private String transferFromUsername;
	private long amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransferToUsername() {
		return transferToUsername;
	}
	public void setTransferToUsername(String transferToUsername) {
		this.transferToUsername = transferToUsername;
	}
	public String getTransferFromUsername() {
		return transferFromUsername;
	}
	public void setTransferFromUsername(String transferFromUsername) {
		this.transferFromUsername = transferFromUsername;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	

}
