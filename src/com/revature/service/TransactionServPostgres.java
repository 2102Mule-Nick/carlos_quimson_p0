package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;
import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;

public class TransactionServPostgres implements TransactionService {
	
	AccountDao accountDao;
	Account account;
	User user;
	UserDao userDao;
	TransactionDao transactionDao;
	
	private Logger log = Logger.getRootLogger();
	
	@Override
	public void deposit(User user, float amount, Account account) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		log.info(account.getAccountType());
		float tempAmount;
		float prevAmount;
		if (amount > 0) {
			prevAmount = account.getBalance();
			tempAmount = account.getBalance() + amount;
			account.setBalance(tempAmount);
			
			//create a new transaction object to send to DB
			Transaction transaction = new Transaction();
			transaction.setUser(user);
			transaction.setAccount(account);
			transaction.setTransactionType("deposit");
			transaction.setPreviousBalance(prevAmount);
			transaction.setNewBalance(tempAmount);
			transactionDao.createTransaction(transaction);
			
			accountDao.updateAccount(account);
			System.out.println("You have deposited $" + amount);
			System.out.println("Your current balance is: \n" + account.getBalance() + "\n");
		}
		else {
			log.error("Amount is invalid");
			throw new IllegalArgumentException(); // needs to be more detailed exception in the future
		}
		
	}

	@Override
	public void withdraw(User user, float amount, Account account) {
		// TODO Auto-generated method stub
		
		float tempAmount = 0;
		
		if ((amount > 0) && (amount < account.getBalance())) {
			tempAmount = account.getBalance() - amount;
			
			//create a new transaction object to send to DB
			Transaction transaction = new Transaction();
			transaction.setUser(user);
			transaction.setAccount(account);
			transaction.setTransactionType("withdraw");
			transaction.setPreviousBalance(account.getBalance());
			transaction.setNewBalance(tempAmount);
			transactionDao.createTransaction(transaction);
			
			account.setBalance(tempAmount);
			accountDao.updateAccount(account);
			System.out.println("Your current account balance is: \n" + account.getBalance() + "\n");
		}
		else {
			log.error("User tried to overdraw account");
			throw new IllegalArgumentException(); // needs to be more detailed exception in the future
		}
			
	}
	
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public float checkBalance(Account account) {
		// TODO Auto-generated method stub
		return account.getBalance();
	}

	public TransactionServPostgres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionServPostgres(User user) {
		super();
		this.user = user;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	
	
}
