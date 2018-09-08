package com.ravi.springboot.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.springboot.Model.User;

@RestController
public class UserController {

    @GetMapping("/getUser")
    public User getUser(@RequestParam(value="name", defaultValue="World") String name) {
        return new User(1, name);
    }
}
