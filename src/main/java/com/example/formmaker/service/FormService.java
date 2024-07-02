package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;

import java.util.List;

public interface FormService {
    Form createForm(Form form);
    List<Form> getAllForms();
    Form getFormById(Long id);
    Form updateFormById(Long id, Form updatedForm);
    Form addQuestionToForm(Long formId, Question question);
    void deleteFormById(Long id);
}
