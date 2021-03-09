package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.User;

public interface AccountDao {
	public void createAccount(Account account);
	
	public List<Account> getAccountsByUser(User user); // list all accounts owned by the same user
	
	public List<Account> getAllAccounts(); //only makes sense for admin users
	
	public void updateAccount(Account account, float amount); // update the balance of the accounts
	
	public void removeAccount(); //deletion of an account but not a user

}
