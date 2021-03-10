package com.revature.util;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionSingleton {
	private Logger log = Logger.getRootLogger();
	
	private static ConnectionSingleton postgresConnect = null;
	
	public Connection establishConnection() {
		
		String forName = "org.postgresql.Driver";
		
		String databaseName = "postgres";
		String defaultConnection = "jdbc:postgresql://" + System.getenv("DB_URL") + ":5432/" + databaseName + "?";
//		defaultConnection += System.getenv("DB_SCHEMA");
		//need to find a way to change schema so it's not the public schema
		String dbUsername = System.getenv("DB_USERNAME");
		String dbPassword = System.getenv("DB_PASS"); 
		//String dbUsername = "postgres";
		//String dbPassword = "bouncer";
		
		Properties props = new Properties();
		props.setProperty("user", dbUsername);
		props.setProperty("password", dbPassword);
		props.setProperty("currentSchema", System.getenv("DB_SCHEMA"));
		
		
		try {
			Class.forName(forName);
			
			log.info("Attempting to connect to database");
			return DriverManager.getConnection(defaultConnection, props);
			//return DriverManager.getConnection(defaultConnection + databaseName, dbUsername, dbPassword);
			
		} catch (SQLException e) {
			log.error("Error opening database using Postgres", e);
			
		} catch (Exception e) {
			log.error("A different exception from SQLException occurred", e);
		}
		
		return null;
	}
	
	public static synchronized Connection getConnection() {
		if (postgresConnect == null) {
			postgresConnect = new ConnectionSingleton();
		}
		
		return postgresConnect.establishConnection();
	}

	public ConnectionSingleton() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
