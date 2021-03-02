package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.pojo.User;
import com.revature.service.AuthService;

public class RegisterMenu implements Menu {

	private Scanner scan;
	private AuthService authService;
	private Menu nextMenu;
	private Menu loginMenu;
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
		System.out.println("Enter your first name");
		String firstName = scan.nextLine();
		System.out.println("Enter your last name");
		String lastName = scan.nextLine();
		System.out.println("Enter a user name of your choice");
		String userName = scan.nextLine();
		System.out.println("Enter a password");
		String password = scan.nextLine();
		System.out.println("Enter initial account balance");
		float balance = Float.parseFloat(scan.nextLine());
		
		User user = new User(userName, password, firstName, lastName, balance);
		
		if (!authService.existingUser(user)) {
			try {
				authService.registeredUser(user);
				nextMenu = loginMenu;
			}
			catch (Exception e) {
				System.out.println("Error. Register again");
				log.info("Error with the user's attempt to register");
			}
		}
		else {
			System.out.println("Error. Register again. Username taken");
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
		// TODO Auto-generated method stub
		this.scan = scan;
	}

	public RegisterMenu(AuthService authService, Menu loginMenu) {
		super();
		this.authService = authService;
		this.loginMenu = loginMenu;
	}

	public RegisterMenu(Scanner scan, AuthService authService, Menu loginMenu, Menu transactionMenu) {
		super();
		this.scan = scan;
		this.authService = authService;
		this.loginMenu = loginMenu;
		this.transactionMenu = transactionMenu;
	}
	
	

}
