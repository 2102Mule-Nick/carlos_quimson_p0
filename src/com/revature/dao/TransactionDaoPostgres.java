package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.pojo.Account;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;
import com.revature.util.ConnectionSingleton;

public class TransactionDaoPostgres implements TransactionDao {

	private Logger log = Logger.getRootLogger();
	
	Connection sqlConnect = null;
	
	@Override
	public void createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		sqlConnect = ConnectionSingleton.getConnection();
		String sqlStatement = "INSERT INTO transactions (user_id, account_id, previous_balance, new_balance, transaction_type)";
		sqlStatement += "values ((SELECT user_id FROM users WHERE username = ?), ?, ?, ?, ?)";
		
		PreparedStatement psqlStatement = null;
		
		String getLastTrans = "SELECT transaction_id FROM TRANSACTIONS ORDER BY transaction_id desc LIMIT 1";
		PreparedStatement getTransId = null;
		
		String callableString = "call calculateChangeAmount(?)";
		CallableStatement callableStmt = null;
		
		int transId = 0;
		
		ResultSet result = null;
		try {

			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setString(1, transaction.getUser().getUsername());
			psqlStatement.setInt(2, transaction.getAccount().getAccount_number());
			psqlStatement.setFloat(3, transaction.getPreviousBalance());
			psqlStatement.setFloat(4, transaction.getNewBalance());
			psqlStatement.setString(5,  transaction.getTransactionType());
			
			psqlStatement.execute();
			
			log.info("Query to get last trans_id");
			getTransId = sqlConnect.prepareStatement(getLastTrans);
			result = getTransId.executeQuery();
			log.info("result: " + result);
			
			while (result.next()) {
				transId = result.getInt("transaction_id");
			}
			log.info("transId: " + transId);
			
			// Using JDBC to call a stored procedure using a callable statement
			callableStmt = sqlConnect.prepareCall(callableString);
			callableStmt.setInt(1, transId);
			callableStmt.execute();
			
			log.info("TransactionDaoPostgres.createTransaction: Created transaction record");
		} catch (SQLException e) {
			log.error("TransactionDaoPostgres.createTransaction: Error creating transaction entry on DB", e);
		}
		
	}

	@Override
	public List<Transaction> getTransactionByAccount(Account account, User user) { //Using user as a lazy method instead of calling DB to search for user
		// TODO Auto-generated method stub
		log.info("TransactionDaoPostgres.getTransactionByUser method called");
		
		Transaction transaction = null;
		
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "SELECT * FROM transactions WHERE account_id = ?";
		
		PreparedStatement psqlStatement = null;
		
		ResultSet result = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setInt(1, account.getAccount_number());
			
			result = psqlStatement.executeQuery();
			
			while (result.next()) {
				transaction = new Transaction();
				//(int transactionNumber, User user, Account account, String transactionType, float previousBalance, float newBalance)
				transaction.setUser(user);
				transaction.setTransactionNumber(result.getInt("transaction_id"));
				transaction.setTransactionType(result.getString("transaction_type"));
				transaction.setPreviousBalance(result.getFloat("previous_balance"));
				transaction.setNewBalance(result.getFloat("new_balance"));
				
				//need to figure out a way to set Account on transaction using account_id
				
				listOfTransactions.add(transaction);
			}
		} catch (SQLException e) {
			log.error("TransactionDaoPostgres.getTransactionsByUser: Error getting all transactions by user");
		}
		
		return listOfTransactions;
	}

	public TransactionDaoPostgres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setConnection(Connection connection) {
		this.sqlConnect = connection;
	}

}
