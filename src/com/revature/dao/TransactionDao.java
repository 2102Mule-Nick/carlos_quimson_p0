package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;

public interface TransactionDao {

	public void createTransaction(Transaction transaction); // create a new transaction
	
	public List<Transaction> getTransactionByAccount(Account account, User user);
	
}
