package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import org.springframework.data.domain.Page;


public interface FormResultService {

    Page<FormResult> getCompletedFormsByUser(int page, int size,Long userId);

    Page<FormResult> getAllCompletedForms(int page, int size);

    Page<FormResult> getCompletedFormsByForm(int page, int size,Long formId);

    FormResult createFormResult(User user, Form form);

}
