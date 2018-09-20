package com.ravi.springboot.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	@Test
	public void test1() {
		Mockito.when(userRepositoryMock.executeRetrieveQuery("")).thenReturn(new ArrayList<User>());
		assertEquals(0, userService.getUsers().size());
	}
}
