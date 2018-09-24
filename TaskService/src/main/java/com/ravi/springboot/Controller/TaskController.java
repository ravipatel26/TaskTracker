package com.ravi.springboot.Controller;

import java.util.ArrayList;
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
	
	@GetMapping("/getPendingTasks/{id}")
    public List<Task> getPendingTasks(@PathVariable("id") int id) {
		return taskService.getPendingTasks(id);
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
		//userService.createUser(user);
	}
    
    @PostMapping("/completeTask/{id}")
    public void editUser(@RequestBody Task task, @PathVariable("id") int id) {
    	//userService.editUser(id, user);
	}
    
    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable("id") int id) {
    	//userService.deleteUser(id);
	}
}
