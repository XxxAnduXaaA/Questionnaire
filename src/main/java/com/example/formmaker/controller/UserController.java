package com.example.formmaker.controller;

import com.example.formmaker.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUserById(@PathVariable @Min(1) Long userId);

    @PostMapping("/user/registration")
    public User createUser(@RequestBody @Valid User user);

    @GetMapping("/user/hello")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> helloWorld();
}
