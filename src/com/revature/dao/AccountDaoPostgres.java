package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "SELECT * FROM accounts WHERE account_owner = ?";
				
		PreparedStatement psqlStatement = null;
		
		ResultSet result = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setString(1,  "SELECT user_id FROM users WHERE username = '" + user.getUsername() + "'");
			
			result = psqlStatement.executeQuery();
			
			while (result.next()) {
				log.info("Accounts for user");
				// need to do more logic here
			}
			
		} catch (SQLException e) {
			log.error("AccountDaoPostgres.getAccountsByUser: Error getting all accounts by user");
		}
		
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account account, float amount) {
		// TODO Auto-generated method stub
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "UPDATE accounts set balance = ? WHERE account_id = ?";
		
		PreparedStatement psqlStatement = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			
			psqlStatement.setFloat(1, amount);
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

}
