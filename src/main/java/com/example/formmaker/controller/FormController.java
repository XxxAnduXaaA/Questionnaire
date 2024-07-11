package com.example.formmaker.controller;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FormController {

    @PostMapping("/adminPanel/form")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    Form createForm(@Valid @RequestBody Form form);
    @GetMapping("/adminPanel/getAllForms")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<Form> getAllForms();
    @GetMapping("/form/{formId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    Form getFormById(@PathVariable    Long formId);
    @PutMapping("/adminPanel/form/{formId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    Form updateFormById(@PathVariable    Long formId,  @RequestBody Form updatedForm);
    @PostMapping("/adminPanel/form/{formId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    Form addQuestionToForm(@PathVariable    Long formId,   @RequestBody Question question);
    @DeleteMapping("/adminPanel/form/{formId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteFormById(@PathVariable   Long formId);
}
