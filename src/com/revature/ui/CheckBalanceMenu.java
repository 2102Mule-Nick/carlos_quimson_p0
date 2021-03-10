package com.revature.ui;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.TransactionService;

public class CheckBalanceMenu implements Menu {

	private Scanner scan;
	private Menu nextMenu;
	private Menu previousMenu;
	private TransactionService transaction;
	private User user;
	private Menu transactionMenu;
	private Account account;
	private AccountDao accountDao;
	
	
	public Menu getTransactionMenu() {
		return transactionMenu;
	}

	public void setTransactionMenu(Menu transactionMenu) {
		this.transactionMenu = transactionMenu;
	}

	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		System.out.println("Your current balance is: $" + account.getBalance());
		//System.out.println(user.getBalance());
		//System.out.println(user.getUsername());
		nextMenu = transactionMenu;
	}

	@Override
	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return previousMenu;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScanner(Scanner scan) {
		// TODO Auto-generated method stub
		this.scan = scan;
	}
	
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CheckBalanceMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckBalanceMenu(Scanner scan, Menu previousMenu, User user) {
		super();
		this.scan = scan;
		this.previousMenu = previousMenu;
		this.user = user;
	}
	
	

}
