package com.revature.ui;

import java.util.Scanner;

public class WelcomeMenu implements Menu {

	private Scanner scan;
	
	private Menu loginMenu;
	
	private Menu registerMenu;
	
	private Menu nextMenu;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// Displays a welcome Menu for the User 
		
		System.out.println("Welcome to the Banking App");
		System.out.println("What would you like to do? Login or register?");
		String input = scan.nextLine();
		
		if ("login".equalsIgnoreCase(input)) {
			nextMenu = loginMenu; 
		} 
		else if("register".equalsIgnoreCase(input)) {
			nextMenu = registerMenu;
		}
		else {
			System.out.println("Invalid choice");
			nextMenu = this; //redisplays this page if the user made an invalid choice
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

	}

	public WelcomeMenu(Scanner scan, Menu loginMenu, Menu registerMenu) {
		super();
		this.scan = scan;
		this.loginMenu = loginMenu;
		this.registerMenu = registerMenu;
	}
	
	

}
