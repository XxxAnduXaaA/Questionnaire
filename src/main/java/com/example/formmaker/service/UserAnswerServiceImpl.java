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

    public void submitFormAnswers(User user, List<UserAnswer> userAnswers) {
        if (user == null || userAnswers.isEmpty() || userAnswers.get(0).getAnswer() == null) {
            throw FormResultException.DATA_IS_NULL();
        }

        Form form = questionsRepository.findByAnswersAnswerId(userAnswers.get(0).getAnswer().getAnswerId()).getForm();
        FormResult formResult = formResultService.createFormResult(user, form);

        List<UserAnswer> readyToSaveUa = userAnswers.stream().map(
                ua -> userAnswerMapper.mapToEntity(ua, user, formResult)).collect(Collectors.toList());
        formResult.setAnswers(readyToSaveUa);
        formResultRepository.save(formResult);
    }
}
