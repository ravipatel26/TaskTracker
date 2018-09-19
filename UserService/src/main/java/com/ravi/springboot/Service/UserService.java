package com.ravi.springboot.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;

@Service
public class UserService {

	private List<User> users = new ArrayList<User>(Arrays.asList(
			new User(2, "Ravi", "Patel", "ravster", "redson", new Date()),
			new User(3, "Bruce", "Wayne", "batman", "gotham", new Date())
	));
	private final String GET_USERS = "select * from user";
	private final String GET_USER_BY_ID = "select * from user where id=%d";
	
	public List<User> getUsers() {
		List<User> users = UserRepository.executeRetrieveQuery(GET_USERS);
		return users;
	}
	
	public User getUser(int id) {
		String query = String.format(GET_USER_BY_ID, id);
		List<User> user = UserRepository.executeRetrieveQuery(query);
		return user.get(0);
	}
	
	public void createUser(User user) {
		users.add(user);
	}
	
	public void editUser(int id, User user) {
		for (User u : users) {
			if (u.getId() == id) {
				users.set(users.indexOf(u), user); // TODO: set everything except id and username
				return;
			}
		}
	}
	
	public void deleteUser(int id) {
		users.removeIf(u -> u.getId() == id);
	}
	
	public boolean isUniqueUsername(String username) {
		List<User> users = UserRepository.executeRetrieveQuery(GET_USERS);
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
}