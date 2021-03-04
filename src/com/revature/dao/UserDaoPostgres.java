package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.pojo.User;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDaoPostgres implements UserDao {
	
	private Logger log = Logger.getRootLogger();
	
	Connection sqlConnect = null;
	
	public Connection getConnection() throws FileNotFoundException {
		
		String forName = "org.postgresql.Driver";
		String defaultConnection = "jdbc:postgresql://localhost:5432/";
		String databaseName = "testdb";
		String dbUsername = "postgres";
		String dbPassword = "bouncer";
		
		try {
			Class.forName(forName);
			sqlConnect = DriverManager.getConnection(defaultConnection + databaseName, dbUsername, dbPassword);
			return sqlConnect;
		} catch (Exception e) {
			log.info("Error opening database using Postgres");
			throw new FileNotFoundException();
		}
	}
	
	public void createTable() {

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
	}
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		try {
			sqlConnect = this.getConnection();
			
			Statement sqlStatement = sqlConnect.createStatement();
			
			String statement = "Any Postgres Statement here";
			sqlStatement.executeUpdate(statement);
			
			sqlStatement.close();
			sqlConnect.commit();
			sqlConnect.close();
			
			log.info("Successfully created user in DB");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Error with creating user to db");
		}
		
		
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = null;
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
				
			}
			
			resultSet.close();
			sqlStatement.close();
			sqlConnect.close();
			
		}
		catch (Exception e) {
			log.info("Error with getting all users from DB");
		}
		
		return users;
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
