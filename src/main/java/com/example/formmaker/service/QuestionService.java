package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Question;

import java.util.List;

public interface QuestionService {
    public Question updateQuestion(Long questionId, Question updatedQuestion);
    public void deleteQuestion(Long questionId);
    public List<Question> findQuestionsByForm(Long formId);


}
