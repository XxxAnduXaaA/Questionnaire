package com.example.formmaker.controller;

import com.example.formmaker.repository.UserRepository;
import com.example.formmaker.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.formmaker.entity.User;

@Controller
public class AuthorizationController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthorizationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getAuthorizationPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityExistsException("Такой email уже существует");
        }
        userService.createUser(user);
        return "redirect:/login";
    }

}
