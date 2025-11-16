package com.example.formmaker.controller;

import com.example.formmaker.constant.Attribute;
import com.example.formmaker.security.MyUserDetails;
import com.example.formmaker.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
public class IndexController {

    private final FormService formService;

    @GetMapping("/")
    public String indexPage(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
        model.addAttribute(Attribute.FORMS, formService.getAllForms());
        model.addAttribute(Attribute.USER, userDetails.getUser());
        return "index";
    }
}
