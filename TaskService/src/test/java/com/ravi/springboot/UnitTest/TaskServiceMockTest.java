//package com.ravi.springboot.UnitTest;
//
//import static org.junit.Assert.*;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.ravi.springboot.Model.Task;
//import com.ravi.springboot.Repository.UserRepository;
//import com.ravi.springboot.Service.UserService;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class TaskServiceMockTest {
//
//	@Mock
//	UserRepository userRepositoryMock;
//	
//	@InjectMocks
//	UserService userService;
//	
//	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	private final String GET_USERS = "select * from user where role='user'";
//	private final String GET_ALL_USERS = "select * from user";
//	private final String GET_USER_BY_ID = "select * from user where id=%d";
//	private final String CREATE_USER = "insert into user (firstname,lastName,dateOfBirth,username,password,role) values ('%s','%s','%s','%s','%s','user');";
//	private final String EDIT_USER = "update user set firstname='%s', lastName='%s', dateOfBirth='%s', password='%s' where id=%d";
//	private final String DELETE_USER = "delete from user where id='%d' and role='user'";
//	private Task user1 = new Task(1, "bruce", "wayne", "batman", "password", new Date(), "user");
//	private Task user2 = new Task(2, "matt", "murdock", "daredevil", "password", new Date(), "user");
//	
//	@Test
//	public void testGetAllUsersWithNoUsersInDatabase() {
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(new ArrayList<Task>());
//		assertEquals(0, userService.getUsers().size());
//	}
//	
//	@Test
//	public void testGetAllUsersWithUsersInDatabase() {	
//		List<Task> actualUserList = Arrays.asList(user1, user2);
//		List<Task> expectedUserList = Arrays.asList(user1, user2);
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(expectedUserList);
//		assertArrayEquals(actualUserList.toArray(), userService.getUsers().toArray());
//	}
//	
//	@Test
//	public void testGetUserByIdWithNoUsersInDatabase() {
//		String query = String.format(GET_USER_BY_ID, 1);
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(new ArrayList<Task>());
//		assertEquals(null, userService.getUser(0));
//		assertEquals(null, userService.getUser(1));
//	}
//	
//	@Test
//	public void testGetUserByIdWithUsersInDatabase() {
//		String query = String.format(GET_USER_BY_ID, 1);
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(Arrays.asList(user1));
//		assertEquals(false, user1.equals(userService.getUser(0)));
//		assertEquals(true, user1.equals(userService.getUser(1)));
//	}
//	
//	@Test
//	public void testCreateUser() {
//		String query = String.format(CREATE_USER, user1.getFirstName(),user1.getLastName(),dateFormat.format(user1.getDateOfBirth()),user1.getUsername(),user1.getPassword());
//		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
//		assertEquals(0, userService.createUser(null));
//		assertEquals(0, userService.createUser(new Task()));
//		assertEquals(1, userService.createUser(user1));
//	}
//	
//	@Test
//	public void testEditUser() {
//		String query = String.format(EDIT_USER, user1.getFirstName(),user1.getLastName(),dateFormat.format(user1.getDateOfBirth()),user1.getPassword(),1);
//		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
//		assertEquals(0, userService.editUser(0, null));
//		assertEquals(0, userService.editUser(1, null));
//		assertEquals(0, userService.editUser(0, new Task()));
//		assertEquals(0, userService.editUser(1, new Task()));
//		assertEquals(0, userService.editUser(0, user1));
//		assertEquals(1, userService.editUser(1, user1));
//	}
//	
//	@Test
//	public void testDeleteUser() {
//		String query = String.format(DELETE_USER, 1);
//		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
//		assertEquals(0, userService.deleteUser(0));
//		assertEquals(1, userService.deleteUser(1));
//	}
//	
//	@Test
//	public void testIsUniqueUsernameWithNoUsersInDatabase() {
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_ALL_USERS)).thenReturn(new ArrayList<Task>());
//		assertEquals(false, userService.isUniqueUsername(null));
//		assertEquals(false, userService.isUniqueUsername(""));
//		assertEquals(true, userService.isUniqueUsername("uniqueName"));
//		assertEquals(true, userService.isUniqueUsername("batman"));
//	}
//	
//	@Test
//	public void testIsUniqueUsernameWithUsersInDatabase() {
//		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_ALL_USERS)).thenReturn(Arrays.asList(user1,user2));
//		assertEquals(false, userService.isUniqueUsername(null));
//		assertEquals(false, userService.isUniqueUsername(""));
//		assertEquals(true, userService.isUniqueUsername("uniqueName"));
//		assertEquals(false, userService.isUniqueUsername("batman"));
//	}
//}