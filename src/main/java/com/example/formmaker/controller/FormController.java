package com.example.formmaker.controller;

import com.example.formmaker.dto.FormAnswersDto;
import com.example.formmaker.entity.User;
import com.example.formmaker.service.FormService;
import com.example.formmaker.service.UserAnswerService;
import com.example.formmaker.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
public class FormController {

    private final FormService formService;
    private final UserAnswerService userAnswerService;
    private final UserService userService;

    public FormController(FormService formService, UserAnswerService userAnswerService, UserService userService) {
        this.formService = formService;
        this.userAnswerService = userAnswerService;
        this.userService = userService;
    }

    @GetMapping("/forms")
    public String getForms(Model model) {
        model.addAttribute("forms", formService.getAllForms());
        return "forms";
    }

    @PostMapping("/form/{formId}")
    public String submitForm(@ModelAttribute FormAnswersDto formAnswersDto, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        userAnswerService.submitFormAnswers(user, formAnswersDto.getUserAnswers());
        return "submit-success";
    }

    @GetMapping("/form/{formId}")
    public String getForm(@PathVariable Long formId, Model model) {
        model.addAttribute("form", formService.getFormById(formId));
        model.addAttribute("formAnswersDto", new FormAnswersDto());
        return "form-view";
    }

}
