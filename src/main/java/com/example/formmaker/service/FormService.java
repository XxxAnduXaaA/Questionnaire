package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;

import java.util.List;
import java.util.Optional;

public interface FormService {
    Form createForm(Form form);
    List<Form> getAllForms();
    Optional<Form> getFormByTitle(String title);
    Form getFormById(Long formId);
    Form updateFormById(Long formId, Form updatedForm);
    void deleteFormById(Long formId);
}
