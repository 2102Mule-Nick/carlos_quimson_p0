package com.revature.service;

import com.revature.pojo.User;

public interface AuthService {

	public boolean existingUser(User user);
	
	public User authenticateUser(User user);
	
	public User registeredUser(User user);

}
