package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDao {

	public void createUser(User user);
	
	public User getUserByUsername(String username); 
	
	public List<User> getAllUsers();
	
	public void updateUser(User user);
	
	public void removeUser(User user);
	
}
