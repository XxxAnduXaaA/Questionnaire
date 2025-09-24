package com.example.formmaker.service;

import com.example.formmaker.entity.*;
import com.example.formmaker.repository.QuestionsRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionsRepository questionsRepository;
    private final FormResultService formResultService;

    public UserAnswerServiceImpl(UserAnswerRepository userAnswerRepository, QuestionsRepository questionsRepository, FormResultService formResultService) {
        this.formResultService = formResultService;
        this.userAnswerRepository = userAnswerRepository;
        this.questionsRepository = questionsRepository;
    }

    @Transactional
    public void submitFormAnswers(User user, List<UserAnswer> userAnswers) {
        List<UserAnswer> readyToSaveUa = new ArrayList<>();
        Form form = questionsRepository.findByAnswers_AnswerId(userAnswers.get(0).getAnswer().getAnswerId()).getForm();
        FormResult formResult = formResultService.createFormResult(user, form);

        for (UserAnswer ua : userAnswers) {
            Question question = questionsRepository.findByAnswers_AnswerId(ua.getAnswer().getAnswerId());
            ua.setUser(user);
            ua.setQuestion(question);
            ua.setForm(form);
            ua.setUserForm(formResult);
            readyToSaveUa.add(ua);
        }
        userAnswerRepository.saveAll(readyToSaveUa);
    }


}
