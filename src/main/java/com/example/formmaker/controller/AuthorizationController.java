package com.example.formmaker.controller;

import com.example.formmaker.constant.Attribute;
import com.example.formmaker.entity.User;
import com.example.formmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthorizationController {

    private final UserService userService;

    @GetMapping("/login")
    public String getAuthorizationPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute(Attribute.USER, new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }
}
