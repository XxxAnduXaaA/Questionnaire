package com.example.formmaker.service;

import com.example.formmaker.entity.User;

public interface UserService {
    User getUserById(Long userId);
    User createUser(User user);
    User findByEmail(String user);
    User changeUserInfo(Long UserId, User updatedUser);


}
