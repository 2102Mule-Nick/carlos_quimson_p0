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
	private Menu openAccountMenu;
	
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
			System.out.println((x) + ": " + this.accounts.get(x).getAccountType() + " | Account ID: " + this.accounts.get(x).getAccount_number() + ": $" + this.accounts.get(x).getBalance());
		}

		System.out.println("Which account would you like to access or type 'open' to create a new account?");
		String response = scan.nextLine();
		
		if (response.equalsIgnoreCase("open")) {
			// insert account open menu here
			
			((OpenAccountMenu) openAccountMenu).setUser(user);
			nextMenu = openAccountMenu;
		} 
		else if (Integer.parseInt(response) < (this.accounts.size()) && (Integer.parseInt(response) >= 0)) {
			((TransactionMenu) transactionMenu).setAccount(this.accounts.get(Integer.parseInt(response)));
			((TransactionMenu) transactionMenu).setUser(user);
			((TransactionMenu) transactionMenu).setAccountDao(accountDao);
			nextMenu = transactionMenu;
		}
		else {
			System.out.println("Invalid choice. Try again");
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

	public Menu getOpenAccountMenu() {
		return openAccountMenu;
	}

	public void setOpenAccountMenu(Menu openAccountMenu) {
		this.openAccountMenu = openAccountMenu;
	}
	
	

}
