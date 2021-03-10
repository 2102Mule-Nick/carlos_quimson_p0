package com.revature.pojo;

public class Transaction {
	private int transactionNumber;
	
	private User user;
	
	private Account account;
	
	private String transactionType;
	
	private float previousBalance;
	
	private float newBalance;
	
	public int getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	
	public float getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(float previousBalance) {
		this.previousBalance = previousBalance;
	}

	public float getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(float newBalance) {
		this.newBalance = newBalance;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transactionNumber, User user, Account account, String transactionType, float previousBalance,
			float newBalance) {
		super();
		this.transactionNumber = transactionNumber;
		this.user = user;
		this.account = account;
		this.transactionType = transactionType;
		this.previousBalance = previousBalance;
		this.newBalance = newBalance;
	}
	
	
}
