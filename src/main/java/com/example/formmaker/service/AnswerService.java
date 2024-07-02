package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;

import java.util.List;

public interface AnswerService {
    public Answer addAnswerToQuestion(Long question_id, Answer answer);
    public Answer updateAnswer(Long question_id, Answer updatedAnswer);
    public void deleteAnswerById(Long answer_id);
    public List<Answer> getAnswersByQuestionId(Long question_id);
    public List<Answer> getCorrectAnswersByFormId(Long form_id);
}
