package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.revature.pojo.User;

public class UserDaoKryo implements UserDao {

	private Kryo kryo = new Kryo();
	
	private Logger log = Logger.getRootLogger();
	
	private static final String FOLDER_NAME = "users/";
	
	private static final String FILE_EXTENSION = ".txt";
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
		log.info("Starting to create user");
		
		//uses kryo to create new user files in the user folders
		try (FileOutputStream outputStream = new FileOutputStream(FOLDER_NAME + user.getUsername() + FILE_EXTENSION)){
			Output output = new Output(outputStream);
			kryo.writeObject(output, user);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Could not open file", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserByUsername(String username) {
		// Gets user information using the username input
		
		String fileName = FOLDER_NAME + username + FILE_EXTENSION;
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		
		try (FileInputStream inputStream = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION)){
			Input input = new Input(inputStream);
			User user = kryo.readObject(input, User.class);
			input.close();
			
			return user;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// Updates the persistent user file. Used whenever a deposit or withdrawal is made
		try (FileOutputStream outputStream = new FileOutputStream(FOLDER_NAME + user.getUsername() + FILE_EXTENSION)){
			Output output = new Output(outputStream);
			kryo.writeObject(output, user);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub

	}

	public UserDaoKryo() {
		super();
		// TODO Auto-generated constructor stub
		kryo.register(User.class);
	}
	
	

}
