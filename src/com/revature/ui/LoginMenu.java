package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.pojo.User;
import com.revature.service.AuthService;

public class LoginMenu implements Menu{

	private AuthService authService;
	
	private Scanner scan;
	
	private Menu nextMenu;
	
	private Menu transactionMenu;
	
	private Logger log = Logger.getRootLogger();
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		System.out.println("Enter your username");
		String username = scan.nextLine();
		System.out.println("Enter your password");
		String password = scan.nextLine();
		
		User user = new User(username, password);
		
		try {
			user = authService.authenticateUser(user);
			((TransactionMenu) transactionMenu).setUser(user);
			nextMenu = transactionMenu;
		}
		catch (Exception e) { //needs to be a more specific Exception here 
			System.out.println("Check your login information and try again.");
			log.info("User entered incorrect login information");
			nextMenu = this;
		}
		
		
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
		this.scan = scan;
		
	}

	public LoginMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoginMenu (AuthService authService, TransactionMenu transactionMenu) {
		super();
		this.authService = authService;
		this.transactionMenu = transactionMenu;
	}

	public LoginMenu(AuthService authService) {
		super();
		this.authService = authService;
	}
}
