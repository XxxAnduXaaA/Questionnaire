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
    @GetMapping("/form/{id}")
    Form getFormById(@PathVariable @Min(1) Long id);
    @PutMapping("/form/{id}")
    Form updateFormById(@PathVariable @Min(1) Long id, @Valid @RequestBody Form updatedForm);
    @PostMapping("/adminPanel/form/{id}")
    Form addQuestionToForm(@PathVariable @Min(1) Long id, @Valid @RequestBody Question question);
    @DeleteMapping("/adminPanel/form/{id}")
    void deleteFormById(@PathVariable @Min(1) Long id);
}
