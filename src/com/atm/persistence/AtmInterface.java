package com.atm.persistence;

import com.atm.bank.BankAccount;

public interface AtmInterface {
	
//	authenticate user
	
	public BankAccount authenticateUser(Integer accountNumber , Integer pin);
	
	public Float withdrawCash(Float amount);
	
	public Float depositCash(Float amount);
	
	public Float checkBalance();


}
