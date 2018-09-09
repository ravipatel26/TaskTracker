package com.ravi.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Service.UserService;

@RestController
public class UserController {

	//@Autowired
	//private UserService userService;
	
    @GetMapping("/getUser")
    public User getUser(@RequestParam(value="name", defaultValue="World") String name) {
        return new User("1", name);
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<User> creatUser(@RequestBody User newUser) {

		User user = newUser;//TODO: userService.createUser(newUser);
		//POST: http://localhost:8080/createUser
		//{
		//	"id": 1,
		//	"name": "ravi"
		//}

		if (user == null)
			return ResponseEntity.noContent().build();

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
