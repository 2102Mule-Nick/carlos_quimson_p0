package com.revature.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.revature.Driver2;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.pojo.User;
import com.revature.service.AuthService;
import com.revature.service.AuthServiceImpl;
import com.revature.service.TransactionServiceImpl;

class Driver2Test {
	
	private static final Driver2 driver2 = new Driver2();

	private static final UserDao userDaoImpl = new UserDaoImpl();
	
	private static final AuthService authServiceImpl = new AuthServiceImpl(userDaoImpl);
	
	private static final User user = new User("carlostest", "1234", "carlos", "test", 50);
	
	private static final User user2 = new User("carlosss", "1234", "carlos", "test", 25);
	
	private static final TransactionServiceImpl transactionService = new TransactionServiceImpl(user);
	
	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	
	/*
	@Test //Sample
	public void testMethodName() {
		assertEquals("Expected Outcome", className.method("argument"));
	}
	*/
	
	@Test
	public void checkExistingUser() {
		assertEquals(true, authServiceImpl.existingUser(user));
	}
	
	@Test
	public void returnsFalseIfUserNotExisting() {
		assertEquals(false, authServiceImpl.existingUser(user2));
	}
	
	@Test
	public void checkUserIsValid() {
		assertEquals(user.getPassword(), authServiceImpl.authenticateUser(user).getPassword());
	}
	
	@Test
	public void checkGetUserName() {
		assertEquals(user.getUsername(), userDaoImpl.getUserByUsername("carlostest").getUsername());
	}
	
	/* Currently unsure of how to test void methods
	@Test
	public void checkDeposits() {
		float result = transactionService.deposit(user, 10);
		assertEquals(60, result);
	}
	*/

}
