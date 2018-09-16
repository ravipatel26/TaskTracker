package com.ravi.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getUsers")
    public List<User> getUsers() {
		return userService.getUsers();
    }
	
    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") int id) {
		return userService.getUser(id);
    }
    
    @PostMapping("/createUser")
    public void creatUser(@RequestBody User user) {
		userService.createUser(user);
	}
    
    @PostMapping("/editUser/{id}")
    public void editUser(@RequestBody User user, @PathVariable("id") int id) {
    	userService.editUser(id, user);
	}
    
    @DeleteMapping("/deleteUser/{id}")
    public void deletetUser(@PathVariable("id") int id) {
    	userService.deleteUser(id);
	}
    
}
