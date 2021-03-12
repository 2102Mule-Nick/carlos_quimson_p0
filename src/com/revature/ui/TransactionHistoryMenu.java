package com.revature.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;
import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;
import com.revature.service.TransactionService;

public class TransactionHistoryMenu implements Menu {

	private Scanner scan;
	private Menu nextMenu;
	private Menu previousMenu;
	private TransactionService transaction;
	private User user;
	private Menu transactionMenu;
	private Account account;
	private AccountDao accountDao;
	private Logger log = Logger.getRootLogger();
	private TransactionDao transactionDao;
	private List<Transaction> transactions = null;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub

		this.setTransactions(transactionDao.getTransactionByAccount(account, user));
		System.out.println("Transaction History List");
		
		for (int x = 0; x < this.transactions.size(); x++) {
			String displayOutput = "Transaction Number: " + transactions.get(x).getTransactionNumber(); //enter formatting of result here
			displayOutput += "| Transaction Type: " + transactions.get(x).getTransactionType();
			displayOutput += "| Previous Balance: $" + transactions.get(x).getPreviousBalance();
			displayOutput += "| New Balance: $" + transactions.get(x).getNewBalance();
			
			System.out.println(displayOutput);
		}
		
		nextMenu = transactionMenu; //ensure transactionMenu is passed from transactionMenu
	}

	@Override
	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return null;
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

	
	
	public Menu getTransactionMenu() {
		return transactionMenu;
	}

	public void setTransactionMenu(Menu transactionMenu) {
		this.transactionMenu = transactionMenu;
	}

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public TransactionHistoryMenu(User user) {
		super();
		this.user = user;
	}

	public TransactionHistoryMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
