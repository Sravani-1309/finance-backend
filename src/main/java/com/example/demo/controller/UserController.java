package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }
}
