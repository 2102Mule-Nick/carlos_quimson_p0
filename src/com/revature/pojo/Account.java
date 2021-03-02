package com.revature.pojo;

public class Account {
	private User accountOwner;
	private int account_number;
	private float balance;
	private String accountType;
	
	public User getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(User accountOwner, int account_number, float balance, String accountType) {
		super();
		this.accountOwner = accountOwner;
		this.account_number = account_number;
		this.balance = balance;
		this.accountType = accountType;
	}
	
	
	
}
