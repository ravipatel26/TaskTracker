package com.ravi.springboot.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	private List<User> users = new ArrayList<User>();
//		Arrays.asList(
//			new User(1, "Ravi", "Patel", "ravster", "redson", new Date()),
//			new User(2, "Bruce", "Wayne", "batman", "gotham", new Date())
//	));
	
	public List<User> getUsers() {
		userRepository.findAll().forEach(users::add);
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