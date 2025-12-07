package com.example.formmaker.service;

import com.example.formmaker.constant.Attribute;
import com.example.formmaker.dto.FormResultsRequestDto;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.exception.FormException;
import com.example.formmaker.exception.FormResultException;
import com.example.formmaker.repository.FormResultRepository;
import com.example.formmaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormResultServiceImpl implements FormResultService {

    private final FormResultRepository formResultRepository;
    private final FormService formService;
    private final UserRepository userRepository;

    @Override
    public Page<FormResult> getCompletedFormsByUser(int page, int size, Long userId) {
        return formResultRepository.findAllByUserId(PageRequest.of(page, size, Sort.by("completedAt").descending()), userId);
    }

    @Override
    public Page<FormResult> getAllCompletedForms(int page, int size) {
        return formResultRepository.findAll(PageRequest.of(page, size, Sort.by("completedAt").descending()));
    }

    @Override
    public Page<FormResult> getCompletedFormsByForm(int page, int size, Long formId) {
        return formResultRepository.findAllByFormFormId(PageRequest.of(page, size, Sort.by("completedAt").descending()), formId);
    }

    @Override
    public Page<FormResult> getFormResults(FormResultsRequestDto formResultsRequestDto) {
        int page = formResultsRequestDto.getPage();
        int size = formResultsRequestDto.getSize();
        Long formId = formResultsRequestDto.getFormId();
        Long userId = formResultsRequestDto.getUserId();

        if (formId != null) {
            return getCompletedFormsByForm(page, size, formId);
        } else if (userId != null) {
            return getCompletedFormsByUser(page, size, userId);
        } else {
            return getAllCompletedForms(page, size);
        }
    }

    @Override
    public void modelFiller(Model model, Page<FormResult> results, int page) {
        model.addAttribute("results", results);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", results.getTotalPages());
        model.addAttribute(Attribute.FORMS, formService.getAllForms());
        model.addAttribute(Attribute.USERS, userRepository.findAll());
    }

    @Override
    public FormResult createFormResult(User user, Form form) {
        if (user == null || form == null) {
            throw FormException.USER_OR_FORM_IS_NULL();
        }
        FormResult formResult = new FormResult();
        formResult.setForm(form);
        formResult.setUser(user);
        return formResult;
    }

    @Override
    public void getUserFormPage(Long userFormId, Model model) {
        FormResult formResult = formResultRepository
                .findById(userFormId)
                .orElseThrow(() -> FormResultException.NOT_FOUND(userFormId));
        List<UserAnswer> userAnswers = formResult.getAnswers();
        Map<Long, List<UserAnswer>> answersByQuestion = userAnswers.stream()
                .collect(Collectors.groupingBy(ua -> ua.getAnswer().getQuestion().getQuestionId()));

        model.addAttribute(Attribute.FORM, formResult.getForm());
        model.addAttribute("answersByQuestion", answersByQuestion);
    }
}

