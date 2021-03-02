package com.revature.service;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface TransactionService {
	
	public void deposit(User user, float amount) throws IllegalArgumentException;
	
	public void withdraw(User user, float amount);
	
	public float checkBalance(User user);
	
}
