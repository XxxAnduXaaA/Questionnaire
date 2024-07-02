package com.example.formmaker.service;

import com.example.formmaker.entity.User;

public interface UserService {
    public User getUserById(Long userId);
    public User createUser(User user);

}
