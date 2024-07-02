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
    public Form getFormById(Long formId) {
        return formService.getFormById(formId);
    }

    @Override
    public Form updateFormById(Long formId, Form updatedForm) {
        return formService.updateFormById(formId, updatedForm);
    }

    @Override
    public Form addQuestionToForm(Long formId, Question question) {
        return formService.addQuestionToForm(formId, question);
    }

    @Override
    public void deleteFormById(Long formId) {
        formService.deleteFormById(formId);
    }
}
