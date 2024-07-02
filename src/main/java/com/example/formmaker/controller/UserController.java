package com.example.formmaker.controller;

import com.example.formmaker.entity.User;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserController {
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable @Min(1) Long userId);
}
