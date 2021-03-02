package com.revature.ui;

import java.util.Scanner;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.TransactionService;

public class DepositsMenu implements Menu {

	private Scanner scan;
	private Menu nextMenu;
	private Menu previousMenu;
	private TransactionService transaction;
	private User user;
	private Menu transactionMenu;
	
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		System.out.println("How much are you depositing today?");
		
		float deposit = Float.parseFloat(scan.nextLine());
		try {
			transaction.deposit(user, deposit); //calls the deposit method in the transaction class
			//System.out.println(user.getBalance());
			//System.out.println(transaction.checkBalance(user));
			nextMenu = transactionMenu;
		}
		catch (IllegalArgumentException e) {
			// need to implement
		}
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}
	
	

	public Menu getTransactionMenu() {
		return transactionMenu;
	}

	public void setTransactionMenu(Menu transactionMenu) {
		this.transactionMenu = transactionMenu;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public DepositsMenu() {
		super();
	}

	public DepositsMenu(Scanner scan, TransactionService transaction) {
		super();
		this.scan = scan;
		this.transaction = transaction;
	}
	
	
	
	

}
