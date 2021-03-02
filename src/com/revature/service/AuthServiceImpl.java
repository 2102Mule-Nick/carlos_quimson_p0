package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.pojo.User;

public class AuthServiceImpl implements AuthService {

	private UserDao userDao;
	@Override
	public boolean existingUser(User user) {
		// TODO Auto-generated method stub
		
		try {
			if (userDao.getUserByUsername(user.getUsername()) != null) {
				return true;
			}
		} 
		catch (Exception e) {
			return false;
		}
		
		return false;
	}

	public User authenticateUser(User user) {
		// TODO Auto-generated method stub
		
		User existingUser = userDao.getUserByUsername(user.getUsername());
		
		if (existingUser.getPassword().equals(user.getPassword())) {
			return existingUser;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public User registeredUser(User user) {
		// TODO Auto-generated method stub
		userDao.createUser(user);
		return user;
	}

	public AuthServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

}
