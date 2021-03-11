package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TransactionDaoTest {

	
	@Mock
	private Connection connection;
	TransactionDaoPostgres daoPostgres;
	
	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PreparedStatement pstmt = ConnectionSingleton.getConnection().prepareStatement("DROP TABLE IF EXISTS users");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("DROP TABLE IF EXISTS accounts");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("DROP TABLE IF EXISTS transactions");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("create table users (\r\n" + 
				"	user_id serial primary key,\r\n" + 
				"	username text not null,\r\n" + 
				"	first_name text not null,\r\n" + 
				"	last_name text not null,\r\n" + 
				"	passcode text not null);");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("create table accounts(\r\n" + 
				"	account_id serial primary key,\r\n" + 
				"	account_type text not null,\r\n" + 
				"	account_owner int references users (user_id),\r\n" + 
				"	balance decimal not null);");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("create table transactions(\r\n" + 
				"	transaction_id serial primary key,\r\n" + 
				"	user_id int references users (user_id),\r\n" + 
				"	account_id int references accounts (account_id),\r\n" + 
				"	previous_balance decimal,\r\n" + 
				"	new_balance decimal,\r\n" + 
				"	change_amount decimal,\r\n" + 
				"	transaction_type text);");
		pstmt.execute();
		
		
	}
	*/
	
	//insert beforeEach and afterEach If applicable
	@BeforeEach
	void setUp() throws Exception {
		daoPostgres = new TransactionDaoPostgres();
		
		PreparedStatement pstmt = ConnectionSingleton.getConnection().prepareStatement("truncate transactions RESTART IDENTITY cascade;");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("truncate accounts RESTART IDENTITY cascade;");
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("truncate users RESTART IDENTITY cascade;");
		pstmt.execute();

		pstmt = ConnectionSingleton.getConnection().prepareStatement("insert into users (username, passcode, first_name, last_name) values ('miketest', '1234', 'mike', 'test')");
		
		pstmt.execute();
		
		pstmt = ConnectionSingleton.getConnection().prepareStatement("insert into accounts (account_type, balance,  account_owner) values ('checking', 50, 1)");
		
		pstmt.execute();
	}
	
	@Test
	void createTransactionTest() throws SQLException {
		Connection realConnection = ConnectionSingleton.getConnection();
		
		String sqlStatement = "INSERT INTO transactions (user_id, account_id, previous_balance, new_balance, transaction_type)";
		sqlStatement += "values ((SELECT user_id FROM users WHERE username = ?), ?, ?, ?, ?)";
		
		PreparedStatement realStmt = realConnection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
		
		PreparedStatement spy = Mockito.spy(realStmt);
		
		
		System.out.println("Connection: " + connection);
		when(connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)).thenReturn(spy);
		
		daoPostgres.setConnection(connection); // need to create a setConnection in TransactionDaoPostgres?
	
		User user = new User("miketest", "1234", "mike", "test");
		
		Account account = new Account(user, 1, 50, "checking");
		
		Transaction transaction = new Transaction();
		transaction.setUser(user);
		transaction.setAccount(account);
		transaction.setTransactionType("deposit");
		transaction.setPreviousBalance(50);
		transaction.setNewBalance(100);
		
		daoPostgres.createTransaction(transaction);
		
		verify(spy).setString(1, transaction.getUser().getUsername());
		verify(spy).setInt(2, transaction.getAccount().getAccount_number());
		verify(spy).setFloat(3, transaction.getPreviousBalance());
		verify(spy).setFloat(4, transaction.getNewBalance());
		verify(spy).setString(5,  transaction.getTransactionType());
		
		verify(spy).executeUpdate();
		
		PreparedStatement checkStmt = ConnectionSingleton.getConnection().prepareStatement("select * from transactions where transaction_id = 1");
	
		ResultSet rs = checkStmt.executeQuery();
		
		assertTrue(rs.next());
	
	}

}
