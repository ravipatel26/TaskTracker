package com.ravi.springboot.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping("/getUsers")
    public List<User> getUsers() { //GET http://localhost:8080/getUsers
        List<User> list = new ArrayList<User>();
        list.add(new User());
        list.add(new User());
		return list; // userService.list();
    }
	
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
    	//GET http://localhost:8080/getUser/1
        User user = new User(); //userService.get(id);
		if (user == null) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
    @PostMapping("/createUser")
    public ResponseEntity<User> creatUser(@RequestBody User newUser) {

		//POST: http://localhost:8080/createUser
		//{
		//	"id": 1,
		//	"name": "ravi"
		//}

    	if (newUser == null)
    		return new ResponseEntity<User>(newUser, HttpStatus.BAD_REQUEST);

    	// userService.createUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
    
    @PostMapping("/editUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User editedUser) {

		User user = editedUser;//TODO: userService.update(id, newUser);

		if (editedUser == null)
			return new ResponseEntity<User>(editedUser, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
    
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Long> deletetUser(@PathVariable("id") Long id) { //TODO: determine if return ResponseEntity<User> instead?

    	User user = new User(); //service.delete(id)
    	if (user == null) {
			return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
    
}
