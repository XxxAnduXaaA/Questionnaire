package com.example.formmaker.controller;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FormController {

    @PostMapping("/adminPanel/form")
    Form createForm(@Valid @RequestBody Form form);
    @GetMapping("/adminPanel/getAllForms")
    List<Form> getAllForms();
    @GetMapping("/form/{formId}")
    Form getFormById(@PathVariable @Min(1) Long formId);
    @PutMapping("/form/{formId}")
    Form updateFormById(@PathVariable @Min(1) Long formId, @Valid @RequestBody Form updatedForm);
    @PostMapping("/adminPanel/form/{formId}")
    Form addQuestionToForm(@PathVariable @Min(1) Long formId, @Valid @RequestBody Question question);
    @DeleteMapping("/adminPanel/form/{formId}")
    void deleteFormById(@PathVariable @Min(1) Long formId);
}
