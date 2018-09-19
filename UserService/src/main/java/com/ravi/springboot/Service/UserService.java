package com.ravi.springboot.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	public List<User> getUsers() {
		List<User> users = UserRepository.executeQuery(GET_USERS);
		return users;
	}
	
	public User getUser(int id) {
		try {
			return users.stream().filter(u -> u.getId() == id).findFirst().get();
		} catch (NoSuchElementException e) {
			return null;
		}
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
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
}