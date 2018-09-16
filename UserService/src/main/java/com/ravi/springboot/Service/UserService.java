package com.ravi.springboot.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.User;

@Service
public class UserService {

	private List<User> users = new ArrayList<User>(Arrays.asList(
			new User(1, "Ravi", "Patel", "ravster", "redson", new Date()),
			new User(2, "Bruce", "Wayne", "batman", "gotham", new Date())
	));
	
	public List<User> getUsers() {
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
}