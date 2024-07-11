package com.example.formmaker.controller;

import com.example.formmaker.entity.User;
import com.example.formmaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController{

    @Autowired
    UserService userService;

    @Override
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("You have entered successfully!");
    }
}
