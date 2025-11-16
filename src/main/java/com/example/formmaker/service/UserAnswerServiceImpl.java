package com.example.formmaker.service;

import com.example.formmaker.entity.*;
import com.example.formmaker.repository.QuestionsRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final QuestionsRepository questionsRepository;
    private final FormResultService formResultService;

    @Transactional
    public void submitFormAnswers(User user, List<UserAnswer> userAnswers) {
        List<UserAnswer> readyToSaveUa = new ArrayList<>();
        Form form = questionsRepository.findByAnswers_AnswerId(userAnswers.get(0).getAnswer().getAnswerId()).getForm();
        FormResult formResult = formResultService.createFormResult(user, form);

        List<UserAnswer> readyToSaveUa = userAnswers.stream().map(
                ua -> userAnswerMapper.mapToEntity(ua, user, formResult)).collect(Collectors.toList());
        formResult.setAnswers(readyToSaveUa);
        formResultRepository.save(formResult);
    }
}
