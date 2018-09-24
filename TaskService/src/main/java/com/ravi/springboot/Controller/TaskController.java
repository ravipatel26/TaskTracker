package com.ravi.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.springboot.Model.Task;
import com.ravi.springboot.Service.TaskService;

@RestController
@CrossOrigin(origins="*")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/getPendingTasks/{userId}")
    public List<Task> getPendingTasks(@PathVariable("userId") int userId) {
		return taskService.getPendingTasks(userId);
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
    	taskService.createTask(task);
	}
    
    @PostMapping("/completeTask")
    public void completeTask(@RequestBody Task task) {
    	taskService.completeTask(task);
	}
    
    @DeleteMapping("/deleteTask/{userId}")
    public void deleteTask(@PathVariable("userId") int userId) {
    	taskService.deleteTasks(userId);
	}
}
