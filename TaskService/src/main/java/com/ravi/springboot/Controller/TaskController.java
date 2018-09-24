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
//import com.ravi.springboot.Service.UserService;

@RestController
@CrossOrigin(origins="*")
public class TaskController {

//	@Autowired
//	private UserService userService;
	
	@GetMapping("/getPendingTasks")
    public List<Task> getPendingTasks() {
		return new ArrayList<Task>();
    }
	
//    @GetMapping("/getUser/{id}")
//    public Task getUser(@PathVariable("id") int id) {
//		return userService.getUser(id);
//    }
//    
//    @PostMapping("/createUser")
//    public void creatUser(@RequestBody Task user) {
//		userService.createUser(user);
//	}
//    
//    @PostMapping("/editUser/{id}")
//    public void editUser(@RequestBody Task user, @PathVariable("id") int id) {
//    	userService.editUser(id, user);
//	}
//    
//    @DeleteMapping("/deleteUser/{id}")
//    public void deletetUser(@PathVariable("id") int id) {
//    	userService.deleteUser(id);
//	}
//    
//    @GetMapping("/isUniqueUsername/{username}")
//    public boolean isUniqueUsername(@PathVariable("username") String username) {
//    	return userService.isUniqueUsername(username);
//    }
}
