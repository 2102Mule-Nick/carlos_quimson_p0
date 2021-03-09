package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.pojo.User;
import com.revature.util.ConnectionSingleton;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoPostgres2 implements UserDao {
	
	private Logger log = Logger.getRootLogger();
	
	Connection sqlConnect = null;
	
	public void createTable() {

		
		/*********************************
		try {
			sqlConnect = this.getConnection();	
			
			Statement sqlStatement = sqlConnect.createStatement();
			
			String statement = "Any Postgres Statement table creation here";
			sqlStatement.executeUpdate(statement);
			
			// don't forget to close the create statement connection and the db connection
			sqlStatement.close();
			sqlConnect.close();
		}
		
		catch (Exception e) {
			
		}
		************************************/
	}
	
	
	@Override
	public void createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "insert into users (username, passcode, first_name, last_name)";
		sqlStatement += " values (?, ?, ?, ?);";
		
		PreparedStatement psqlStatement = null;
		log.info("username: " + user.getUsername() +" password: " + user.getPassword());
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			log.info("Creating Prepared Statement");
			psqlStatement.setString(1, user.getUsername());
			psqlStatement.setString(2, user.getPassword());
			psqlStatement.setString(3, user.getFirst_name());
			psqlStatement.setString(4, user.getLast_name());
			log.info("About to execute prepared Statement");
			psqlStatement.executeUpdate();
			sqlConnect.close();
			log.info("Created new user");
		} catch (SQLException e) {
			log.info("Unsuccessful user creation");
			throw new SQLException();
		}
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		log.info("UserDaoPostgres getUserByUsername method called");
		
		sqlConnect = ConnectionSingleton.getConnection();
		
		String sqlStatement = "select * from users where username = ?";
		
		PreparedStatement psqlStatement = null;
		
		ResultSet result = null;
		
		try {
			log.info("UserDaoPostgres2.getUserByUsername: preparing prepared statement");
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);
			psqlStatement.setString(1, username);
			result = psqlStatement.executeQuery();
			
			while (result.next()) {
				log.info("User is in DB");
				User user = new User();
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("passcode"));
				user.setFirst_name(result.getString("first_name"));
				user.setLast_name(result.getString("last_name"));
				log.info("User to be returned to AuthServices");
				return user;
			}
		} catch (SQLException e) {
			log.error("UserDaoPostgres2.getUserByUsername: Error returning user from DB");
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = null;
		
		/*
		try {
			sqlConnect = this.getConnection();
			
			Statement sqlStatement = sqlConnect.createStatement();
			
			String queryStatement = "Enter SELECT * Query here";
			
			ResultSet resultSet = sqlStatement.executeQuery(queryStatement);
			
			while (resultSet.next()) {
				//code block for handling results from db
				// use "users" list that was initialize as a variable for resultSet
				/*
				 * int id = rs.getInt("id");
		         * String  name = rs.getString("name");
		         * int age  = rs.getInt("age");
		         * String  address = rs.getString("address");
		         * float salary = rs.getFloat("salary");
				 * 
				 */
		/*		
			}
			
			resultSet.close();
			sqlStatement.close();
			sqlConnect.close();
			
		}
		catch (Exception e) {
			log.info("Error with getting all users from DB");
		}
		*/
		
		return users;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		log.info("UserDaoPostgres updateUser method called");
		
		sqlConnect = ConnectionSingleton.getConnection();
		
		//String sqlStatement = "update users set balance = ? WHERE username = ?";
		String sqlStatement = "update users set first_name = ?, last_name = ?, passcode = ? WHERE username = ?";
		
		PreparedStatement psqlStatement = null;
		
		try {
			psqlStatement = sqlConnect.prepareStatement(sqlStatement);

			psqlStatement.setString(1, user.getFirst_name());
			psqlStatement.setString(2, user.getLast_name());
			psqlStatement.setString(3, user.getPassword());
			psqlStatement.setString(4, user.getUsername());

			psqlStatement.executeUpdate();
			log.info("User account updated");
		} catch (SQLException e) {
			log.info("Unsuccessful update", e);
		}
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
