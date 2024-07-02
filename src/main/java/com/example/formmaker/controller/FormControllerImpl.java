package com.example.formmaker.controller;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.service.FormService;
import com.example.formmaker.service.FormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FormControllerImpl implements FormController{

    @Autowired
    private FormService formService;

    @Override
    public Form createForm(Form form) {
        return formService.createForm(form);
    }

    @Override
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @Override
    public Form getFormById(Long id) {
        return formService.getFormById(id);
    }

    @Override
    public Form updateFormById(Long id, Form updatedForm) {
        return formService.updateFormById(id, updatedForm);
    }

    @Override
    public Form addQuestionToForm(Long id, Question question) {
        return formService.addQuestionToForm(id, question);
    }

    @Override
    public void deleteFormById(Long id) {
        formService.deleteFormById(id);
    }
}
