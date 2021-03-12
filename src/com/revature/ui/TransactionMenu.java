package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;

public class TransactionMenu implements Menu {

	private Menu nextMenu;
	private Scanner scan;
	private Menu depositsMenu;
	private Menu withdrawalsMenu;
	private Menu checkBalanceMenu;
	private User user;
	private Logger log = Logger.getRootLogger();
	private Account account;
	private AccountDao accountDao;
	private Menu transactionHistoryMenu;
	
	@Override
	public Menu advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// TODO Auto-generated method stub
		//System.out.println("What transaction would you like to do?");
		System.out.println("\nWhat transaction would you like to do on your " + account.getAccountType() + " account " + user.getFirst_name() + " " + user.getLast_name());
		System.out.println("1 for deposits");
		System.out.println("2 for withdrawals");
		System.out.println("3 to check your balance");
		System.out.println("4 to view your transaction history");
		System.out.println("0 to logout");
		//System.out.println(user.getUsername());
		
		String transactionResponse = scan.nextLine();
		
		switch(transactionResponse) {
			case "1":
				//depositsMenu = new DepositsMenu(account); //creates a new menu instance and attaches it to depositsMenu
				 //creates a new menu instance and attaches it to depositsMenu
				((DepositsMenu) depositsMenu).setUser(user);
				((DepositsMenu) depositsMenu).setAccount(account);
				((DepositsMenu) depositsMenu).setAccountDao(accountDao);
				nextMenu = depositsMenu; //depositsMenu
				break;
			case "2":
				((WithdrawalsMenu) withdrawalsMenu).setUser(user);
				((WithdrawalsMenu) withdrawalsMenu).setAccount(account);
				((WithdrawalsMenu) withdrawalsMenu).setAccountDao(accountDao);
				nextMenu = withdrawalsMenu;
				break;
			case "3":
				//nextMenu = new CheckBalanceMenu(scan, this, user);
				((CheckBalanceMenu) checkBalanceMenu).setUser(user);
				((CheckBalanceMenu) checkBalanceMenu).setAccount(account);
				((CheckBalanceMenu) checkBalanceMenu).setAccountDao(accountDao);
				nextMenu = checkBalanceMenu;
				break;
			case "4":
				((TransactionHistoryMenu) transactionHistoryMenu).setAccount(account);
				((TransactionHistoryMenu) transactionHistoryMenu).setUser(user);
				nextMenu = transactionHistoryMenu;
				break;
			case "0":
				System.out.println("You are now logged out. Thank you for using this app.");
				log.info("User has logged out");
				System.exit(0);
			default:
				System.out.println("Invalid Choice");
				nextMenu = this; //returns to this menu if invalid choice made
		}
	}

	@Override
	public Menu previousMenu() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setScanner(Scanner scan) {
		// TODO Auto-generated method stub
		this.scan = scan;
	}

	public Menu getTransactionHistoryMenu() {
		return transactionHistoryMenu;
	}

	public void setTransactionHistoryMenu(Menu transactionHistoryMenu) {
		this.transactionHistoryMenu = transactionHistoryMenu;
	}

	public TransactionMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionMenu(Menu depositsMenu, Menu withdrawalsMenu, Menu checkBalanceMenu) {
		this.depositsMenu = depositsMenu;
		this.withdrawalsMenu = withdrawalsMenu;
		this.checkBalanceMenu = checkBalanceMenu;
	}
	
	
	public TransactionMenu(Menu depositsMenu, Menu withdrawalsMenu, Menu checkBalanceMenu, User user) {
		this.depositsMenu = depositsMenu;
		this.withdrawalsMenu = withdrawalsMenu;
		this.checkBalanceMenu = checkBalanceMenu;
		this.user = user;
	}
	
	

}
