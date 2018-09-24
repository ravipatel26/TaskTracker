package com.ravi.springboot.UnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ravi.springboot.Model.Task;
import com.ravi.springboot.Repository.TaskRepository;
import com.ravi.springboot.Service.TaskService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskServiceMockTest {

	@Mock
	TaskRepository taskRepositoryMock;
	
	@InjectMocks
	TaskService taskService;
	
	private final String GET_TASKS = "select * from task where userId=%d and taskStatus='pending'";
	private final String CREATE_TASK = "insert into task (taskDescription,taskStatus,userId) values('%s','pending',%d)";	
	private final String COMPLETE_TASK = "update task set taskStatus='complete' where id=%d";
	private final String DELETE_TASKS = "delete from task where userId=%d";
	private int userId = 1;
	private Task task1 = new Task(1, "task description 1", "pending", userId);
	private Task task2 = new Task(2, "task description 1", "completed", userId);
	
	@Test
	public void testGetAllTasksWithNoTasksInDatabase() {
		String query = String.format(GET_TASKS, userId);
		Mockito.when(taskRepositoryMock.executeRetrieveQuery(query)).thenReturn(new ArrayList<Task>());
		assertEquals(0, taskService.getPendingTasks(userId).size());
	}
	
	@Test
	public void testGetAllTasksWithTasksInDatabase() {
		String query = String.format(GET_TASKS, userId);
		List<Task> actualTaskList = Arrays.asList(task1, task2);
		List<Task> expectedTaskList = Arrays.asList(task1, task2);
		Mockito.when(taskRepositoryMock.executeRetrieveQuery(query)).thenReturn(expectedTaskList);
		assertArrayEquals(actualTaskList.toArray(), taskService.getPendingTasks(userId).toArray());
	}
	
	@Test
	public void testCreateTask() {
		String query = String.format(CREATE_TASK, task1.getDescription(), task1.getUserId());
		Mockito.when(taskRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, taskService.createTask(null));
		assertEquals(0, taskService.createTask(new Task()));
		assertEquals(1, taskService.createTask(task1));
	}

	@Test
	public void testCompleteTask() {
		String query = String.format(COMPLETE_TASK, task1.getId());
		Mockito.when(taskRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, taskService.completeTask(null));
		assertEquals(0, taskService.completeTask(new Task()));
		assertEquals(1, taskService.completeTask(task1));
	}
	
	@Test
	public void testDeleteTask() {
		String query = String.format(DELETE_TASKS, userId);
		Mockito.when(taskRepositoryMock.executeUpdateQuery(query)).thenReturn(1);
		assertEquals(0, taskService.deleteTasks(0));
		assertEquals(1, taskService.deleteTasks(userId));
	}
}
