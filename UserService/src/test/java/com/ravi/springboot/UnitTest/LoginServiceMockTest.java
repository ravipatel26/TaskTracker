package com.ravi.springboot.UnitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;
import com.ravi.springboot.Service.LoginService;;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginServiceMockTest {

	@Mock
	UserRepository userRepositoryMock;
	
	@InjectMocks
	LoginService loginService;
	
	private final String GET_USER_BY_CREDENTIALS = "select * from user where username='%s' and password='%s'";
	private final String USERNAME_MAP_KEY = "username";
	private final String PASSWORD_MAP_KEY = "password";
	private User user;
	private Map<String, Object> validCredentials;
	private Map<String, Object> nullCredentials;
	private Map<String, Object> invalidCredentials;
	
	@Before
	public void setUp() {
		user = new User(1, "bruce", "wayne", "admin", "password", new Date(), "user");
		validCredentials = new HashMap<String, Object>();
		nullCredentials = new HashMap<String, Object>();
		invalidCredentials = new HashMap<String, Object>();
		
		validCredentials.put(USERNAME_MAP_KEY, "admin");
		validCredentials.put(PASSWORD_MAP_KEY, "password");
		nullCredentials.put(USERNAME_MAP_KEY, null);
		nullCredentials.put(PASSWORD_MAP_KEY, null);
		invalidCredentials.put(USERNAME_MAP_KEY, "test");
		invalidCredentials.put(PASSWORD_MAP_KEY, "invalidpassword");
	}
	
	@Test
	public void testLoginForNullOrEmptyCredentialsMap() {
		assertEquals(null, loginService.login(null));
		assertEquals(null, loginService.login(new HashMap<String, Object>()));
	}
	
	@Test
	public void testLoginWithNoUsersInDatabase() {
		String query = String.format(GET_USER_BY_CREDENTIALS, "admin", "password");
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(new ArrayList<User>());
		assertEquals(null, loginService.login(validCredentials));
		assertEquals(null, loginService.login(nullCredentials));
		assertEquals(null, loginService.login(invalidCredentials));
	}
	
	@Test
	public void testLoginWithUsersInDatabase() {
		String query = String.format(GET_USER_BY_CREDENTIALS, "admin", "password");
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(Arrays.asList(user));
		assertEquals(true, user.equals(loginService.login(validCredentials)));
		assertEquals(false, user.equals(loginService.login(nullCredentials)));
		assertEquals(false, user.equals(loginService.login(invalidCredentials)));
	}
}
