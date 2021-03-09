package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;

public class AccountDaoImpl implements AccountDao {

	@Override
	public void createAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> getAccountsByUser() { // list all accounts owned by the same user
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() { //only makes sense for admin users
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount() { // update the balance of the accounts
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAccount() { //deletion of an account but not a user
		// TODO Auto-generated method stub

	}

}
