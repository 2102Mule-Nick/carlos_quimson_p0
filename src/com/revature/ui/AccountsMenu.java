package com.revature.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;

public class AccountsMenu implements Menu {

	private Menu nextMenu;
	private Scanner scan;
	private Menu depositsMenu;
	private Menu withdrawalsMenu;
	private Menu checkBalanceMenu;
	private User user;
	private Logger log = Logger.getRootLogger();
	private List<Account> accounts = null;
	private AccountDao accountDao;
	private Menu transactionMenu;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		this.setAccounts(accountDao.getAccountsByUser(user));
		
		System.out.println("Available accounts:");
		
		
		//iterate or limit the number of accounts to 2?
		for (int x = 0; x < this.accounts.size(); x++) {
			System.out.println((x) + ": " + this.accounts.get(x).getAccountType());
		}

		System.out.println("Which account would you like to access?");
		String response = scan.nextLine();
		
		//switch to asking for checking or saving if applicable
		if (Integer.parseInt(response) > (this.accounts.size()-1) || (Integer.parseInt(response) < 0)) {
			//log.info("User chose an invalid option");
			System.out.println("Invalid choice. Try again");
			nextMenu = this;
		}
		
		((TransactionMenu) transactionMenu).setAccount(this.accounts.get(Integer.parseInt(response)));
		((TransactionMenu) transactionMenu).setUser(user);
		((TransactionMenu) transactionMenu).setAccountDao(accountDao);
		nextMenu = transactionMenu;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Menu getTransactionMenu() {
		return transactionMenu;
	}

	public void setTransactionMenu(Menu transactionMenu) {
		this.transactionMenu = transactionMenu;
	}

	public AccountsMenu(Menu nextMenu, Menu depositsMenu, Menu withdrawalsMenu, Menu checkBalanceMenu) {
		super();
		this.nextMenu = nextMenu;
		this.depositsMenu = depositsMenu;
		this.withdrawalsMenu = withdrawalsMenu;
		this.checkBalanceMenu = checkBalanceMenu;
	}
	
	public AccountsMenu(Menu depositsMenu, Menu withdrawalsMenu, Menu checkBalanceMenu) {
		super();
		this.depositsMenu = depositsMenu;
		this.withdrawalsMenu = withdrawalsMenu;
		this.checkBalanceMenu = checkBalanceMenu;
	}

	public AccountsMenu(Menu depositsMenu, Menu withdrawalsMenu, Menu checkBalanceMenu, AccountDao accountDao) {
		super();
		this.depositsMenu = depositsMenu;
		this.withdrawalsMenu = withdrawalsMenu;
		this.checkBalanceMenu = checkBalanceMenu;
		this.accountDao = accountDao;
	}

	public AccountsMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AccountsMenu(AccountDao accountDao, Menu transactionMenu) {
		super();
		this.accountDao = accountDao;
		this.transactionMenu = transactionMenu;
	}

}
