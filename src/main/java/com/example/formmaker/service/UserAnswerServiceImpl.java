package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.repository.AnswerRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService{

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public int scoreCounter(Long userId, Long formId){
        List<UserAnswer> userAnswers = userAnswerRepository.findByUserIdAndFormId(userId, formId);
        int score = 0;

        if (!userAnswers.isEmpty()){
            for(UserAnswer userAnswer : userAnswers){
                Answer correctAnswer = answerRepository.findAnswerByQuestionAndIsCorrectTrue(userAnswer.getQuestion().getQuestionId());
                if(userAnswer.getAnswer().getAnswerId().equals(correctAnswer.getAnswerId())){
                    score++;
                }
            }
        }

        return score;
    }


    @Override
    public List<UserAnswer> getAnswerByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.findByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public List<UserAnswer> getByUserIdAndFormId(Long userId, Long formId) {
        return userAnswerRepository.findByUserIdAndFormId(userId, formId);
    }

    @Override
    public List<UserAnswer> getByUserId(Long userId) {
        return userAnswerRepository.findByUserId(userId);
    }
}
