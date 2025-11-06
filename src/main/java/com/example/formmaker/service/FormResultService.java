package com.example.formmaker.service;

import com.example.formmaker.dto.FormResultsRequestDto;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;


public interface FormResultService {

    Page<FormResult> getCompletedFormsByUser(int page, int size, Long userId);

    Page<FormResult> getAllCompletedForms(int page, int size);

    Page<FormResult> getCompletedFormsByForm(int page, int size, Long formId);

    Page<FormResult> getFormResults(FormResultsRequestDto formResultsRequestDt);

    void modelFiller(Model model, Page<FormResult> results, int page);

    FormResult createFormResult(User user, Form form);

    void getUserFormPage(Long userFormId, Model model);
}
