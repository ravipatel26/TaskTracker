package com.ravi.springboot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ravi.springboot.Model.Task;
import com.ravi.springboot.Repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository = new TaskRepository();

	private final String GET_TASKS = "select * from task where userId=%d and taskStatus='pending'";
	private final String CREATE_TASK = "insert into task (taskDescription,taskStatus,userId) values('%s','pending',%d)";	
	private final String COMPLETE_TASK = "update task set taskStatus='complete' where id=%d";
	private final String DELETE_TASKS = "delete from task where userId=%d";
			
	public List<Task> getPendingTasks(int userId) {
		if (userId < 1)
			return new ArrayList<Task>();
		String query = String.format(GET_TASKS, userId);
		List<Task> tasks = taskRepository.executeRetrieveQuery(query);
		return tasks;
	}
	
	public int createTask(Task task) {
		if (task == null || task.getDescription() == null || task.getUserId() < 1)
			return 0;
		String query = String.format(CREATE_TASK, task.getDescription(), task.getUserId());
		return taskRepository.executeUpdateQuery(query);
	}
	
	public int completeTask(Task task) {
		if (task == null || doesTaskHaveInvalidFields(task))
			return 0;
		String query = String.format(COMPLETE_TASK, task.getId());
		return taskRepository.executeUpdateQuery(query);
	}
	
	public int deleteTasks(int userId) {
		if (userId < 1)
			return 0;
		String query = String.format(DELETE_TASKS, userId);
		return taskRepository.executeUpdateQuery(query);
	}
	
	private boolean doesTaskHaveInvalidFields(Task task) {
		return task.getId() < 1 ||
			   task.getUserId() < 1 ||
			   task.getStatus() == null ||
			   task.getDescription() == null;
	}
}