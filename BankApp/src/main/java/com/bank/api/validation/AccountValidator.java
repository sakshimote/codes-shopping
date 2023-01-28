package com.bank.api.validation;

import com.bank.api.model.Account;

public class AccountValidator {
	public boolean accountValidate(Account account) {
		return (validateBalance(account.getBalance()) && validateType(account.getAccountType()));		
		
	}
	
	private boolean validateType(String type) {
		 return type.equalsIgnoreCase("fixed deposit") || type.equalsIgnoreCase("savings")|| type.equalsIgnoreCase("current");
	}
	
	 private boolean validateBalance(Long balance) {
	        return balance > 0;
	    }

}
