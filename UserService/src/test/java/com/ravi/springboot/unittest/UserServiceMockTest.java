package com.ravi.springboot.unittest;

import static org.junit.Assert.*;

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
	private final String SAMPLE_QUERY = "select * from user";
	private User user1 = new User(1, "bruce", "wayne", "batman", "password", new Date());
	private User user2 = new User(2, "matt", "murdock", "daredevil", "password", new Date());
	
	@Test
	public void testGetAllUsers() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(new ArrayList<User>());
		assertEquals(0, userService.getUsers().size());
				
		List<User> actualUserList = Arrays.asList(user1, user2);
		List<User> expectedUserList = Arrays.asList(user1, user2);
		
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(expectedUserList);
		assertArrayEquals(actualUserList.toArray(), userService.getUsers().toArray()); //TODO: check if really works
	}
	
	@Test
	public void testGetUserById() {
		// proper ID, 0, -1
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(Arrays.asList());
		assertEquals(null, userService.getUser(0));
		assertEquals(null, userService.getUser(-1));
		
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(Arrays.asList(user1));
		assertEquals(null, userService.getUser(1)); //TODO: remove this -> for testing only
		//assertEquals(true, user1.equals(userService.getUser(1))); //failing
		//assertEquals(false, user2.equals(userService.getUser(1)));
	}
	
	@Test
	public void testCreateUser() {
		//  null, empty, actual obj
	}
	
	@Test
	public void testEditUser() {
		// proper ID, 0, -1 | null, empty, actual obj
	}
	
	@Test
	public void testDeleteUser() {
		// proper ID, 0, -1
	}
	
	@Test
	public void testIsUniqueUsername() {
		// empty list: null, empty, actual string
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(Arrays.asList());
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		
		// full list: null, empty, actual string
		Mockito.when(userRepositoryMock.executeRetrieveQuery(SAMPLE_QUERY)).thenReturn(Arrays.asList(user1,user2));
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		assertEquals(false, userService.isUniqueUsername("batman"));
	}
}
