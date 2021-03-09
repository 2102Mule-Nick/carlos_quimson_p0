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
	
	private Menu accountsMenu;
	
	private Logger log = Logger.getRootLogger();
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// Prompts user to enter their log in information
		System.out.println("Enter your username");
		String username = scan.nextLine();
		System.out.println("Enter your password");
		String password = scan.nextLine();
		
		User user = new User(username, password);
		
		try { //passes user information to authService to authenticate the username and password. if valid, user object gets passed to transacitonMenu
			user = authService.authenticateUser(user);
			((AccountsMenu) accountsMenu).setUser(user);
			nextMenu = accountsMenu;
			
			/* Original method prior to use of SQL
			((TransactionMenu) transactionMenu).setUser(user);
			nextMenu = transactionMenu; //passes a transactionMenu with the user set as the next menu
			*/
			
			
		}
		catch (Exception e) { //catches exceptions from the authService
			System.out.println("Check your login information and try again.");
			log.info("User entered incorrect login information");
			nextMenu = this; //displays the same menu to give the user another chance to enter their log in information
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
	
	public LoginMenu(AuthService authService, Menu accountsMenu) {
		super();
		this.authService = authService;
		this.accountsMenu = accountsMenu;
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
