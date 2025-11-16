package com.example.formmaker.service;

import com.example.formmaker.constant.UserRoles;
import com.example.formmaker.entity.User;
import com.example.formmaker.exception.UserException;
import com.example.formmaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserException.USER_NOT_FOUND(userId));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw UserException.EMAIL_ALREADY_TAKEN(user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(UserRoles.USER);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserException.EMAIL_NOT_FOUND(email));
    }

    @Override
    public User changeUserInfo(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> UserException.USER_NOT_FOUND(userId));

        if (updatedUser.getUsername() != null
                && !updatedUser.getUsername().isBlank()
                && !updatedUser.getUsername().equals(user.getUsername())) {
            user.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getEmail() != null
                && !updatedUser.getEmail().isBlank()
                && !updatedUser.getEmail().equals(user.getEmail())) {
            user.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        return userRepository.save(user);
    }
}
