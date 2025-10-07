package com.example.formmaker.controller;

import com.example.formmaker.security.MyUserDetails;
import com.example.formmaker.service.FormService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
public class IndexController {

    private final FormService formService;

    public IndexController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/")
    public String indexPage(Model model, @AuthenticationPrincipal MyUserDetails userDetails){
        model.addAttribute("forms", formService.getAllForms());
        model.addAttribute("user", userDetails.getUser());

        return "index";
    }

}
