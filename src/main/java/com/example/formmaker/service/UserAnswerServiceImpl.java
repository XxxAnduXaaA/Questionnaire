package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.exception.FormResultException;
import com.example.formmaker.repository.FormResultRepository;
import com.example.formmaker.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {

    private final QuestionsRepository questionsRepository;
    private final FormResultService formResultService;
    private final UserAnswerMapper userAnswerMapper;
    private final FormResultRepository formResultRepository;

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
