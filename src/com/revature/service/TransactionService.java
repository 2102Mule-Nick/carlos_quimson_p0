package com.revature.service;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface TransactionService {
	
	public void deposit(User user, float amount, Account account) throws IllegalArgumentException;
	
	public void withdraw(User user, float amount, Account account);
	
	public float checkBalance(Account account);
	
}
