package com.ravi.springboot.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;

@Service
public class LoginService {

	private UserRepository userRepository = new UserRepository();
	private final String GET_USER_BY_USERNAME = "select * from user where username='%s' and password='%s'";
	private final String USERNAME_MAP_KEY = "username";
	private final String PASSWORD_MAP_KEY = "password";
	
	public User login(Map<String, Object> credentials) {
		if (isInvalidCredential(credentials))
			return null;

		String query = String.format(GET_USER_BY_USERNAME, credentials.get(USERNAME_MAP_KEY), credentials.get(PASSWORD_MAP_KEY));
		List<User> users = userRepository.executeRetrieveQuery(query);
		return users.size() > 0 ? users.get(0) : null;
	}
	
	private boolean isInvalidCredential(Map<String, Object> credentials) {
		return credentials == null || credentials.isEmpty() ||
			   !credentials.containsKey(USERNAME_MAP_KEY) || !credentials.containsKey(PASSWORD_MAP_KEY);
	}
}
