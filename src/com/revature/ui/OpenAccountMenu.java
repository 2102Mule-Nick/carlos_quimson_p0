package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.service.AuthService;

public class OpenAccountMenu implements Menu {

	private Scanner scan;
	private Menu nextMenu;
	private Menu loginMenu;
	private Menu transactionMenu;
	private Logger log = Logger.getRootLogger();
	private User user;
	private AccountDao accountDao;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		System.out.println("Enter initial balance: ");
		float balance = Float.parseFloat(scan.nextLine());
		System.out.println("Enter account type: (checking/saving)");
		String type = scan.nextLine();
		
		if (balance > 0 && (type.equalsIgnoreCase("checking") || type.equalsIgnoreCase("saving"))){
		
			Account account = new Account();
			account.setAccountOwner(user);
			account.setBalance(balance);
			account.setAccountType(type);
			
			accountDao.createAccount(account);
			
			nextMenu = loginMenu;
		} else {
			System.out.println("Invalid account information. Try again");
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Menu getLoginMenu() {
		return loginMenu;
	}

	public void setLoginMenu(Menu loginMenu) {
		this.loginMenu = loginMenu;
	}

	
	
}
