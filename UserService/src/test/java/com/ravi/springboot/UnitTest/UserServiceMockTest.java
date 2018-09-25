package com.ravi.springboot.UnitTest;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Repository.UserRepository;
import com.ravi.springboot.Service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceMockTest {

	@Mock
	UserRepository userRepositoryMock;
	
	@InjectMocks
	UserService userService;
	
	private DateFormat dateFormat;
	private final String GET_USERS = "select * from user where role='user'";
	private final String GET_ALL_USERS = "select * from user";
	private final String GET_USER_BY_ID = "select * from user where id=%d";
	private final String CREATE_USER = "insert into user (firstname,lastName,dateOfBirth,username,password,role) values ('%s','%s','%s','%s','%s','user');";
	private final String EDIT_USER = "update user set firstname='%s', lastName='%s', dateOfBirth='%s', password='%s' where id=%d";
	private final String DELETE_USER = "delete from user where id='%d' and role='user'";
	private User user1;
	private User user2;
	private MockRestServiceServer mockServer;
	
	@Before
    public void setUp() {
		RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        user1 = new User(1, "bruce", "wayne", "batman", "password", new Date(), "user");
    	user2 = new User(2, "matt", "murdock", "daredevil", "password", new Date(), "user");
    	dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
	
	@Test
	public void testGetAllUsersWithNoUsersInDatabase() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(new ArrayList<User>());
		assertEquals(0, userService.getUsers().size());
	}
	
	@Test
	public void testGetAllUsersWithUsersInDatabase() {	
		List<User> actualUserList = Arrays.asList(user1, user2);
		List<User> expectedUserList = Arrays.asList(user1, user2);
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_USERS)).thenReturn(expectedUserList);
		assertArrayEquals(actualUserList.toArray(), userService.getUsers().toArray());
	}
	
	@Test
	public void testGetUserByIdWithNoUsersInDatabase() {
		String query = String.format(GET_USER_BY_ID, 1);
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(new ArrayList<User>());
		assertEquals(null, userService.getUser(0));
		assertEquals(null, userService.getUser(1));
	}
	
	@Test
	public void testGetUserByIdWithUsersInDatabase() {
		String query = String.format(GET_USER_BY_ID, 1);
		Mockito.when(userRepositoryMock.executeRetrieveQuery(query)).thenReturn(Arrays.asList(user1));
		assertEquals(false, user1.equals(userService.getUser(0)));
		assertEquals(true, user1.equals(userService.getUser(1)));
	}
	
	@Test
	public void testCreateUser() {
		String formattedDOB = LocalDate.parse(dateFormat.format(user1.getDateOfBirth())).plusDays(1).toString();
		String query = String.format(CREATE_USER, user1.getFirstName(),user1.getLastName(),formattedDOB,user1.getUsername(),user1.getPassword());
		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, userService.createUser(null));
		assertEquals(0, userService.createUser(new User()));
		assertEquals(1, userService.createUser(user1));
	}
	
	@Test
	public void testEditUser() {
		String formattedDOB = LocalDate.parse(dateFormat.format(user1.getDateOfBirth())).plusDays(1).toString();
		String query = String.format(EDIT_USER, user1.getFirstName(),user1.getLastName(),formattedDOB,user1.getPassword(),1);
		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, userService.editUser(0, null));
		assertEquals(0, userService.editUser(1, null));
		assertEquals(0, userService.editUser(0, new User()));
		assertEquals(0, userService.editUser(1, new User()));
		assertEquals(0, userService.editUser(0, user1));
		assertEquals(1, userService.editUser(1, user1));
	}
	
	@Test
	public void testDeleteUser() {
		mockServer.expect(ExpectedCount.once(), MockRestRequestMatchers.requestTo("http://localhost:8081/deleteTask/1"))
        		  .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
        		  .andRespond(MockRestResponseCreators.withSuccess("", MediaType.TEXT_PLAIN));
		
		String query = String.format(DELETE_USER, 1);
		Mockito.when(userRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, userService.deleteUser(0));
		assertEquals(0, userService.deleteUser(1));
	}
	
	@Test
	public void testIsUniqueUsernameWithNoUsersInDatabase() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_ALL_USERS)).thenReturn(new ArrayList<User>());
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		assertEquals(true, userService.isUniqueUsername("batman"));
	}
	
	@Test
	public void testIsUniqueUsernameWithUsersInDatabase() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery(GET_ALL_USERS)).thenReturn(Arrays.asList(user1,user2));
		assertEquals(false, userService.isUniqueUsername(null));
		assertEquals(false, userService.isUniqueUsername(""));
		assertEquals(true, userService.isUniqueUsername("uniqueName"));
		assertEquals(false, userService.isUniqueUsername("batman"));
	}
}
