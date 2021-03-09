package com.revature.ui;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.TransactionService;

public class WithdrawalsMenu implements Menu {

	private Scanner scan;
	private Menu nextMenu;
	private Menu previousMenu;
	private TransactionService transaction;
	private User user;
	private Menu transactionMenu;
	private Account account;
	private AccountDao accountDao;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		System.out.println("How much are you withdrawing today?");
		
		try {
			float withdraw = Float.parseFloat(scan.nextLine());
			transaction.withdraw(user, withdraw, account); //calls the deposit method in the transaction class
			nextMenu = transactionMenu;
		}
		catch (IllegalArgumentException e) {
			// need to implement
			System.out.println("You're withdrawing more than you have");
			nextMenu = this;
		}
		
	}

	@Override
	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return previousMenu;
	}
	
	

	public WithdrawalsMenu(Scanner scan, TransactionService transaction) {
		super();
		this.scan = scan;
		this.transaction = transaction;
	}

	public Menu getTransactionMenu() {
		return transactionMenu;
	}

	public void setTransactionMenu(Menu transactionMenu) {
		this.transactionMenu = transactionMenu;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScanner(Scanner scan) {
		// TODO Auto-generated method stub

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	
	
}
