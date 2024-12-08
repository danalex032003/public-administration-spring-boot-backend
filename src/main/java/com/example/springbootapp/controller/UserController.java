package com.example.springbootapp.controller;

import com.example.springbootapp.model.user.User;
import com.example.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println(user);
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return userService.verify(user);
    }
}
