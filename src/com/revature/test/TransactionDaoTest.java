package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.dao.TransactionDaoPostgres;
import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;
import com.revature.util.ConnectionSingleton;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionDaoTest {

	
	@Mock
	private Connection connection;
	TransactionDaoPostgres daoPostgres;
	
	//insert beforeEach and afterEach If applicable
	@BeforeEach
	void setUp() throws Exception {
		daoPostgres = new TransactionDaoPostgres();
		
		PreparedStatement pstmt = ConnectionSingleton.getConnection().prepareStatement("delete from transactions");
		
		pstmt.execute();
	}
	
	@Test
	void createTransactionTest() throws SQLException {
		Connection realConnection = ConnectionSingleton.getConnection();
		
		String sqlStatement = "INSERT INTO transactions (user_id, account_id, previous_balance, new_balance, transaction_type)";
		sqlStatement += "values ((SELECT user_id FROM users WHERE username = ?), ?, ?, ?, ?)";
		
		PreparedStatement realStmt = realConnection.prepareStatement(sqlStatement);
		
		PreparedStatement spy = Mockito.spy(realStmt);
		
		when(connection.prepareStatement(sqlStatement)).thenReturn(spy);
		
		daoPostgres.setConnection(connection); // need to create a setConnection in TransactionDaoPostgres?
	
		User user = new User("miketest", "1234", "mike", "test");
		
		Account account = new Account(user, 123, 50, "checking");
	
		Transaction transaction = new Transaction();
		transaction.setUser(user);
		transaction.setAccount(account);
		transaction.setTransactionType("deposit");
		transaction.setPreviousBalance(50);
		transaction.setNewBalance(100);
		
		daoPostgres.createTransaction(transaction);
		
		verify(spy).setString(1, user.getUsername());
		verify(spy).setInt(2, account.getAccount_number());
		verify(spy).setFloat(3, transaction.getPreviousBalance());
		verify(spy).setFloat(4, transaction.getNewBalance());
		verify(spy).setString(5,  transaction.getTransactionType());
		
		PreparedStatement checkStmt = ConnectionSingleton.getConnection().prepareStatement("select * from transactions where transaction_id = 1");
	
		ResultSet rs = checkStmt.executeQuery();
		
		assertTrue(rs.next());
	
	}

}
