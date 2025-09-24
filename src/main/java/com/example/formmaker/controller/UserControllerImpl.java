package com.example.formmaker.controller;

import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.repository.FormResultRepository;
import com.example.formmaker.service.FormResultService;
import com.example.formmaker.service.FormService;
import com.example.formmaker.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserControllerImpl {
    private final UserService userService;
    private final FormResultService formResultService;
    private final FormService formService;
    private final FormResultRepository formResultRepository;

    public UserControllerImpl(UserService userService, FormResultService formResultService, FormService formService, FormResultRepository formResultRepository) {
        this.userService = userService;
        this.formResultService = formResultService;
        this.formService = formService;
        this.formResultRepository = formResultRepository;
    }

    @GetMapping("/{userId}/profile")
    public String getProfile(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("completedForms", formResultService.getCompletedFormsByUser(page, size, userId));
        model.addAttribute("currentUser", user);
        return "user/profile";
    }

    @PutMapping("/{userId}/profile")
    public String changeUserInfo(@PathVariable Long userId, @ModelAttribute("currentUser") User updatedUser) {
        userService.changeUserInfo(userId, updatedUser);
        return "user/profile";
    }

    @GetMapping("/{userId}/profile/{userFormId}")
    public String getUserFormPage(@PathVariable Long userId, @PathVariable Long userFormId, Model model) {
        FormResult formResult = formResultRepository.findById(userFormId).orElseThrow(() -> new RuntimeException("FormResult with " + userFormId + " not found"));
        model.addAttribute("form", formResult.getForm());
        List<UserAnswer> userAnswers = formResult.getAnswers();
        Map<Long, List<UserAnswer>> answersByQuestion = userAnswers.stream()
                .collect(Collectors.groupingBy(ua -> ua.getQuestion().getQuestionId()));

        model.addAttribute("answersByQuestion", answersByQuestion);
        return "user/userForm";
    }

}
