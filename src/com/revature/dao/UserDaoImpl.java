package com.revature.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.revature.pojo.User;

public class UserDaoImpl implements UserDao{

	public static List<User> userList; // single list available as a list of user. temporary until Kryo is involved
	
	public UserDaoImpl() {
		super();
		System.out.println("UserDaoImpl Constructor");
		userList = new ArrayList<>();
		userList.add(new User("carlosq", "1234", "carlos", "q", 50));
		userList.add(new User("carlo", "1234"));
		userList.add(new User("carlostest", "1234", "carlos", "q", 50));
	}
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
		// add input verification
		
		userList.add(user);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		
		//System.out.println("UserDao getUserByUserName");
		
		Iterator<User> iter = userList.iterator();
		
		while (iter.hasNext()) {
			
			User existingUser = iter.next();
			//System.out.println(existingUser.getUsername());
			//System.out.println("Username: " + username);
			if (existingUser.getUsername().equals(username)) {
				return existingUser;
			}
		}
		
		throw new IllegalArgumentException();
		
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userList;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
