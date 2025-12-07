package com.example.formmaker.controller;

import com.example.formmaker.constant.Attribute;
import com.example.formmaker.dto.FormResultsRequestDto;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.service.FormResultService;
import com.example.formmaker.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin-panel/form")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;
    private final FormResultService formResultService;

    @GetMapping
    public String getFormPanel(Model model) {
        model.addAttribute(Attribute.FORMS, formService.getAllForms());
        return "admin/forms";
    }

    @GetMapping("/new")
    public String getFormCreator(Model model) {
        Form form = formService.createBlankForm();
        model.addAttribute(Attribute.FORM, form);
        return "admin/form-create";
    }

    @PostMapping("/new")
    public String createForm(@ModelAttribute Form form) {
        formService.createForm(form);
        return "redirect:/admin-panel/form";
    }

    @GetMapping("/{formId}/edit")
    public String getFormUpdater(@PathVariable Long formId, Model model) {
        model.addAttribute(Attribute.FORM, formService.getFormById(formId));
        return "admin/form-edit";
    }

    @PutMapping("/{formId}/edit")
    public String updateFormById(@PathVariable Long formId, @ModelAttribute Form form) {
        formService.updateFormById(formId, form);
        return "redirect:/form/{formId}";
    }

    @DeleteMapping("/{formId}")
    public String deleteFormById(@PathVariable Long formId) {
        formService.deleteFormById(formId);
        return "redirect:/admin-panel/forms";
    }

    @GetMapping("/results")
    public String getFormsResult(@ModelAttribute FormResultsRequestDto formResultsRequestDto, Model model) {
        Page<FormResult> results = formResultService.getFormResults(formResultsRequestDto);
        formResultService.modelFiller(model, results, results.getTotalPages());
        return "admin/formsResult";
    }

    @GetMapping("/results/{userFormId}")
    public String getUserFormPage(@PathVariable Long userFormId, Model model) {
        formResultService.getUserFormPage(userFormId, model);
        return "user/userForm";
    }
}
