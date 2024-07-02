package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Question;

import java.util.List;

public interface QuestionService {
    public Question updateQuestion(Long question_id, Question updatedQuestion);
    public void deleteQuestion(Long question_id);
    public List<Question> findQuestionsByForm(Long form_id);


}
