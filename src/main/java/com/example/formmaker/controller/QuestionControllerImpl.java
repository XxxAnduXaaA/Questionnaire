package com.example.formmaker.controller;

import com.example.formmaker.entity.Question;
import com.example.formmaker.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionControllerImpl implements QuestionController {

    @Autowired
    private QuestionService questionService;

    @Override
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        return questionService.updateQuestion(questionId, updatedQuestion);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionService.deleteQuestion(questionId);
    }

    @Override
    public List<Question> findQuestionsByForm(Long formId) {
        return questionService.findQuestionsByForm(formId);
    }
}
