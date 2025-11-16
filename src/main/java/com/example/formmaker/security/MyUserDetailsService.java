package com.example.formmaker.security;

import com.example.formmaker.entity.User;
import com.example.formmaker.exception.UserException;
import com.example.formmaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserException.EMAIL_NOT_FOUND(email));

        return new MyUserDetails(user);
    }
}
