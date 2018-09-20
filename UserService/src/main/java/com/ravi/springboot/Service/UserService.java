package com.ravi.springboot.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;

@Service
public class UserService {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private UserRepository userRepository = new UserRepository();

	private final String GET_USERS = "select * from user";
	private final String GET_USER_BY_ID = "select * from user where id=%d";
	private final String CREATE_USER = "insert into user (firstname,lastName,dateOfBirth,username,password) values ('%s','%s','%s','%s','%s');";
	private final String EDIT_USER = "update user set firstname='%s', lastName='%s', dateOfBirth='%s', password='%s' where id=%d";
	private final String DELETE_USER = "delete from user where id='%d'";
			
	public List<User> getUsers() {
		List<User> users = userRepository.executeRetrieveQuery(GET_USERS);
		return users;
	}
	
	public User getUser(int id) {
		String query = String.format(GET_USER_BY_ID, id);
		List<User> user = userRepository.executeRetrieveQuery(query);
		return user.get(0);
	}
	
	public int createUser(User user) {
		String query = String.format(CREATE_USER, user.getFirstName(),user.getLastName(),dateFormat.format(user.getDateOfBirth()),user.getUsername(),user.getPassword());
		return userRepository.executeUpdateQuery(query);
	}
	
	public int editUser(int id, User user) {
		String query = String.format(EDIT_USER, user.getFirstName(),user.getLastName(),dateFormat.format(user.getDateOfBirth()),user.getPassword(),id);
		return userRepository.executeUpdateQuery(query);
	}
	
	public int deleteUser(int id) {
		String query = String.format(DELETE_USER, id);
		return userRepository.executeUpdateQuery(query);
	}
	
	public boolean isUniqueUsername(String username) {
		List<User> users = userRepository.executeRetrieveQuery(GET_USERS);
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
}