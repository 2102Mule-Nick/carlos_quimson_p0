package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.dao.UserDaoKryo;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.AuthService;
import com.revature.service.AuthServiceImpl;
import com.revature.service.TransactionService;
import com.revature.service.TransactionServiceImpl;
import com.revature.ui.CheckBalanceMenu;
import com.revature.ui.DepositsMenu;
import com.revature.ui.LoginMenu;
import com.revature.ui.Menu;
import com.revature.ui.RegisterMenu;
import com.revature.ui.TransactionMenu;
import com.revature.ui.WelcomeMenu;
import com.revature.ui.WithdrawalsMenu;

/*
 * This is a simple banking app
 * The user is required to choose between "login" or "register" at the beginning of the app
 * - the user will be reprompted if the user chooses and invalid choice. 
 * 
 * Once registered, the user is allowed to check their account balance, make deposits, and withdrawals.
 * 
 * Usernames have to be unique, and passwords are required. 
 */


public class Driver2 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		UserDao userDao = new UserDaoKryo();
		
		CheckBalanceMenu checkBalanceMenu = new CheckBalanceMenu();
		
		TransactionService transactionService = new TransactionServiceImpl();
		
		((TransactionServiceImpl) transactionService).setUserDao(userDao);
		
		DepositsMenu depositsMenu = new DepositsMenu(scan, transactionService);
		
		WithdrawalsMenu withdrawalsMenu = new WithdrawalsMenu(scan, transactionService);
		
		depositsMenu.setScanner(scan);
		
		TransactionMenu transactionMenu = new TransactionMenu(depositsMenu, withdrawalsMenu, checkBalanceMenu);
		
		depositsMenu.setTransactionMenu(transactionMenu);
		
		withdrawalsMenu.setTransactionMenu(transactionMenu);
		
		checkBalanceMenu.setTransactionMenu(transactionMenu);
		
		AuthService authService = new AuthServiceImpl(userDao);
		
		Menu loginMenu = new LoginMenu(authService, transactionMenu);
		
		Menu registerMenu = new RegisterMenu(scan, authService, loginMenu, transactionMenu);
		
		WelcomeMenu welcomeMenu = new WelcomeMenu(scan, loginMenu, registerMenu);
		
		Menu nextMenu = welcomeMenu;
		
		transactionMenu.setScanner(scan);
		
		
		
		loginMenu.setScanner(scan);
		
		do {
			nextMenu.displayOptions();
			nextMenu = nextMenu.advance();
		} while (nextMenu != null);
	}
}
