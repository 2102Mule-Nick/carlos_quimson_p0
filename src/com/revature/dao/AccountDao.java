package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;

public interface AccountDao {
	public void createAccount();
	
	public List<Account> getAccountsByUser();
	
	public List<Account> getAllAccounts();
	
	public void updateAccount();
	
	public void removeAccount();
}
