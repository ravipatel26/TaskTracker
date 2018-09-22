package com.ravi.springboot.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.springboot.Model.User;
import com.ravi.springboot.Service.LoginService;

@RestController
@CrossOrigin(origins="*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public User login(@RequestBody Map<String, Object> credentials) {
		System.out.println(credentials); //TODO: remove this
		return loginService.login(credentials);
	}
}
