package com.revature.service;

import org.apache.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.pojo.Account;
import com.revature.pojo.User;

public class TransactionServiceImpl implements TransactionService {
	
	User user;
	UserDao userDao;
	
	private Logger log = Logger.getRootLogger();
	
	@Override
	public void deposit(User user, float amount) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		float tempAmount;
		if (amount > 0) {
			tempAmount = user.getBalance() + amount;
			user.setBalance(tempAmount);
			userDao.updateUser(user);
		}
		else {
			log.error("Amount is invalid");
			throw new IllegalArgumentException(); // needs to be more detailed exception in the future
		}
		
	}

	@Override
	public void withdraw(User user, float amount) {
		// TODO Auto-generated method stub
		
		float tempAmount = 0;
		
		if ((amount > 0) && (amount < user.getBalance())) {
			tempAmount = user.getBalance() - amount;
			
			
			user.setBalance(tempAmount);
			userDao.updateUser(user);
			System.out.println(user.getBalance());
		}
		else {
			log.error("User tried to overdraw account");
			throw new IllegalArgumentException(); // needs to be more detailed exception in the future
		}
			
	}
	
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public float checkBalance(User user) {
		// TODO Auto-generated method stub
		return user.getBalance();
	}

	public TransactionServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionServiceImpl(User user) {
		super();
		this.user = user;
	}
	

}
