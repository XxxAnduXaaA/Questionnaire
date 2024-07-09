package com.example.formmaker.controller;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerControllerImpl implements AnswerController{

    @Autowired
    private AnswerService answerService;

    @Override
    public Answer addAnswerToQuestion(Long questionId, Answer answer) {
        return answerService.addAnswerToQuestion(questionId, answer);
    }

    @Override
    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        return answerService.updateAnswer(answerId, updatedAnswer);
    }

    @Override
    public void deleteAnswerById(Long answerId) {
        answerService.deleteAnswerById(answerId);
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerService.getAnswersByQuestion(questionId);
    }

    @Override
    public Answer getCorrectAnswerByQuestionId(Long questionId) {
        return answerService.getCorrectAnswerByQuestion(questionId);
    }
}
