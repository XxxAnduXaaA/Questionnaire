package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.UserAnswer;

import java.util.List;

public interface UserAnswerService {
   public List<UserAnswer> getAnswerByUserIdAndQuestionId(Long userId, Long questionId);
   public List<UserAnswer> getByUserIdAndFormId(Long userId, Long formId);
   public List<UserAnswer> getByUserId(Long userId);

}
