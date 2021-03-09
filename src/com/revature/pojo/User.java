package com.revature.pojo;

public class User {
	private String username;
	
	private String password;
	
	private String first_name;
	
	private String last_name;
	
	//private float balance; removed balance from user and creating an account class

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public User() {
		super();
	}
	
	public User(String username, String password, String first_name, String last_name) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	/*
	public User(String username, String password, String first_name, String last_name, float balance) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		//this.balance = balance;
	}*/
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = "default first name";
		this.last_name = "default last name";
		
	}
	
	public User(String username) {
		super();
		this.username = username;
		this.password = null;
		this.first_name = "default first name";
		this.last_name = "default last name";
	}

	/*
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	*/
	
}
