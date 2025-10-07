package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import com.example.formmaker.repository.FormResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class FormResultServiceImpl implements FormResultService {
    private final FormResultRepository formResultRepository;

    public FormResultServiceImpl(FormResultRepository formResultRepository) {
        this.formResultRepository = formResultRepository;
    }

    @Override
    public Page<FormResult> getCompletedFormsByUser(int page, int size, Long userId) {
        return formResultRepository.findAllByUserId(PageRequest.of(page, size, Sort.by("completedAt").descending()), userId);
    }

    @Override
    public Page<FormResult> getCompletedFormsByForm(int page, int size, Long formId){
        return formResultRepository.findAllByForm_FormId(PageRequest.of(page, size, Sort.by("completedAt").descending()), formId);
    }

    @Override
    public Page<FormResult> getAllCompletedForms(int page, int size) {
        return formResultRepository.findAll(PageRequest.of(page, size, Sort.by("completedAt").descending()));
    }

    @Override
    public FormResult createFormResult(User user, Form form) {
        FormResult formResult = new FormResult();
        formResult.setForm(form);
        formResult.setUser(user);
        return formResultRepository.save(formResult);
    }
}
