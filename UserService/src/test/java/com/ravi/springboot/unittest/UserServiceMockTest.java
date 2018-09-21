package com.ravi.springboot.unittest;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;
import com.ravi.springboot.Service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceMockTest {

	@Mock
	UserRepository userRepositoryMock;
	
	@InjectMocks
	UserService userService;
	
	// TODO: Setup
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final String GET_USERS = "select * from user";
	private final String GET_USER_BY_ID = "select * from user where id=%d";
	private final String CREATE_USER = "insert into user (firstname,lastName,dateOfBirth,username,password) values ('%s','%s','%s','%s','%s');";
	private final String EDIT_USER = "update user set firstname='%s', lastName='%s', dateOfBirth='%s', password='%s' where id=%d";
	private final String DELETE_USER = "delete from user where id='%d'";
	private User user1 = new User(1, "bruce", "wayne", "batman", "password", new Date());
	private User user2 = new User(2, "matt", "murdock", "daredevil", "password", new Date());
	
	@Test
	public void testGetAllUsers() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(new ArrayList<User>());
		assertEquals(0, userService.getUsers().size());
				
		List<User> actualUserList = Arrays.asList(user1, user2);
		List<User> expectedUserList = Arrays.asList(user1, user2);
		
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(expectedUserList);
		assertArrayEquals(actualUserList.toArray(), userService.getUsers().toArray());
	}
	
	@Test
	public void testGetUserById() {
		String query = String.format(GET_USER_BY_ID, 1);
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(new ArrayList<User>());
		assertEquals(null, userService.getUser(0));
		assertEquals(null, userService.getUser(-1));
		assertEquals(null, userService.getUser(1));
		
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(Arrays.asList(user1));
		assertEquals(true, user1.equals(userService.getUser(1)));
		assertEquals(false, user2.equals(userService.getUser(1)));
	}
	
	@Test
	public void testCreateUser() {
		String query = String.format(CREATE_USER, user1.getFirstName(),user1.getLastName(),dateFormat.format(user1.getDateOfBirth()),user1.getUsername(),user1.getPassword());
		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, userService.createUser(null));
		assertEquals(0, userService.createUser(new User()));
		assertEquals(1, userService.createUser(user1));
	}
	
	@Test
	public void testEditUser() {
		// proper ID, 0, -1 | null, empty, actual obj
		// make sure id and username not changed
	}
	
	@Test
	public void testDeleteUser() {
		String query = String.format(DELETE_USER, 1);
		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(null, userService.getUser(0));
		assertEquals(null, userService.getUser(-1));
		assertEquals(null, userService.getUser(1));
	}
	
	@Test
	public void testIsUniqueUsername() {
		// When there are no users in DB
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(new ArrayList<User>());
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		
		// When there are users in DB
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(Arrays.asList(user1,user2));
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		assertEquals(false, userService.isUniqueUsername("batman"));
	}
}
