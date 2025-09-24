package com.example.formmaker.service;

import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserAnswer;

import java.util.List;

public interface UserAnswerService {
    void submitFormAnswers(User user, List<UserAnswer> userAnswers);


}
