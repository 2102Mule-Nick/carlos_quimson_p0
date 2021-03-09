package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.ConnectionSingleton;

public class AccountDaoPostgres implements AccountDao {

	private Logger log = Logger.getRootLogger();
	
	Connection sqlConnect = null;
	
	@Override
	public void createAccount(Account account) {
		// TODO Auto-generated method stub
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "insert into accounts (account_type, balance,  account_owner)";
		sqlStatement += "values (?, ?, ?);";
		
		PreparedStatement psqlStatement = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setString(1, account.getAccountType());
			psqlStatement.setFloat(2,  account.getBalance());
			psqlStatement.setString(3, "SELECT user_id from users where username = '" + account.getAccountOwner().getUsername() + "'");
			
			psqlStatement.execute();
			log.info("AccountDaoPostgres: Account created");
		} catch (SQLException e) {
			log.error("AccountDaoPostgres.createAccount: SQLException Error", e);
		}
	}

	@Override
	public List<Account> getAccountsByUser(User user) {
		// TODO Auto-generated method stub
		log.info("AccountDaoPostgres.getAccountsByUser method called");
		
		Account account = null;
		
		List<Account> listOfAccounts = new ArrayList<Account>();
		
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "SELECT * FROM accounts WHERE account_owner = (SELECT user_id FROM users where username = ?)";
		
		//String sqlStatement2 = "SELECT user_id FROM users WHERE username = ?";
				
		PreparedStatement psqlStatement = null;
		//PreparedStatement psqlStatement2 = null;
		
		//ResultSet userFromSql = null;
		ResultSet result = null;
		//int user_id = 0;
		
		try {
			log.info("User: " + user.getUsername());
			
			/*
			psqlStatement2 = sqlConnect.prepareStatement(sqlStatement2);
			psqlStatement2.setString(1, user.getUsername());
			userFromSql = psqlStatement2.executeQuery();
			
			while (userFromSql.next()) {
				user_id =  userFromSql.getInt("user_id");
			}
			log.info("userFromSql: " + userFromSql.getInt("user_id"));
			*/
			
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setString(1, user.getUsername());

			//psqlStatement.setInt(1, 1); // hardcoded for testing
			//psqlStatement.setString(1,  "SELECT user_id FROM users WHERE username = '" + user.getUsername() + "'");
			
			result = psqlStatement.executeQuery();
			
			while (result.next()) {
				// need to do more logic here to handle <Accounts>
				account = new Account();
				//(User accountOwner, int account_number, float balance, String accountType)
				account.setAccount_number(result.getInt("account_id"));
				account.setAccountType(result.getString("account_type"));
				account.setBalance(result.getFloat("balance"));
				//get account owner from users table
				account.setAccountOwner(user);
				
				listOfAccounts.add(account);
			}
			
		} catch (SQLException e) {
			log.error("AccountDaoPostgres.getAccountsByUser: Error getting all accounts by user");
			//throw an error here
		}
		
		return listOfAccounts;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "UPDATE accounts set balance = ? WHERE account_id = ?";
		
		PreparedStatement psqlStatement = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			
			psqlStatement.setFloat(1, account.getBalance());
			psqlStatement.setInt(2,  account.getAccount_number());
			
			psqlStatement.executeUpdate();
			log.info("AccountDaoPostgres.updateAccount: Update Successful");
		} catch (SQLException e) {
			log.error("AccountDaoPostgres.updateAccount: Balance update Error");
		}
	}

	@Override
	public void removeAccount() {
		// TODO Auto-generated method stub

	}

	public AccountDaoPostgres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
