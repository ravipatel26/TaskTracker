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
	private final String CREATE_USER = "insert into user (firstname,lastName,dateOfBirth,username,password,role) values ('%s','%s','%s','%s','%s','user');";
	private final String EDIT_USER = "update user set firstname='%s', lastName='%s', dateOfBirth='%s', password='%s' where id=%d";
	private final String DELETE_USER = "delete from user where id='%d' and role='user'";
			
	public List<User> getUsers() {
		List<User> users = userRepository.executeRetrieveQuery(GET_USERS);
		return users;
	}
	
	public User getUser(int id) {
		if (id < 1)
			return null;
		String query = String.format(GET_USER_BY_ID, id);
		List<User> users = userRepository.executeRetrieveQuery(query);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	public int createUser(User user) {
		if (user == null || doesUserHaveNullFields(user))
			return 0;
		String query = String.format(CREATE_USER, user.getFirstName(),user.getLastName(),dateFormat.format(user.getDateOfBirth()),user.getUsername(),user.getPassword());
		return userRepository.executeUpdateQuery(query);
	}
	
	public int editUser(int id, User user) {
		if (id < 1 || user == null || doesUserHaveNullFields(user))
			return 0;
		String query = String.format(EDIT_USER, user.getFirstName(),user.getLastName(),dateFormat.format(user.getDateOfBirth()),user.getPassword(),id);
		return userRepository.executeUpdateQuery(query);
	}
	
	public int deleteUser(int id) {
		if (id < 1)
			return 0;
		String query = String.format(DELETE_USER, id);
		// TODO: call task service to delete all tasks associated to user
		return userRepository.executeUpdateQuery(query);
	}
	
	public boolean isUniqueUsername(String username) {
		if (username == null || username.equals(""))
			return false;
			
		List<User> users = userRepository.executeRetrieveQuery(GET_USERS);
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean doesUserHaveNullFields(User user) {
		return user.getFirstName() == null ||
			   user.getLastName() == null ||
			   user.getUsername() == null ||
			   user.getPassword() == null ||
			   user.getDateOfBirth() == null;
	}
}