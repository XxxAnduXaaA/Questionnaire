package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;

import java.util.List;

public interface AnswerService {
    public Answer addAnswerToQuestion(Long questionId, Answer answer);
    public Answer updateAnswer(Long questionId, Answer updatedAnswer);
    public void deleteAnswerById(Long answerId);
    public List<Answer> getAnswersByQuestionId(Long questionId);
//    public List<Answer> getCorrectAnswersByFormId(Long form_id);  TODO: Добавить в form
    public Answer getCorrectAnswerByQuestionId(Long questionId);
}
