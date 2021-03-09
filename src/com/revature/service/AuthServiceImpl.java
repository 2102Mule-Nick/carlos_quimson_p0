package com.revature.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.pojo.User;

public class AuthServiceImpl implements AuthService {

	private Logger log = Logger.getRootLogger();
	
	private UserDao userDao;
	@Override
	public boolean existingUser(User user) {
		// Determines if a username / user is already registered. 
		// accepts a user object as an argument
		
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
		// Makes sure that the password provided for the User object is the password associated for the User
		log.info("AuthServiceImpl.authenticateUser method called");
		User existingUser = userDao.getUserByUsername(user.getUsername());
		
		if (existingUser.getPassword().equals(user.getPassword())) {
			return existingUser;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public User registeredUser(User user) throws Exception {
		// adds the user to the persistent files. Was supposed to be registerUser
		log.info("AuthServiceImpl: Calling passed userDao.createUser");
		try {
			userDao.createUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("AuthService registeredUser method error", (e));
			throw new SQLException();
		}
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
