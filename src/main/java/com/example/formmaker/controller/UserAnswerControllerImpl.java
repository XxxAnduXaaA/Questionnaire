package com.example.formmaker.controller;

import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAnswerControllerImpl implements UserAnswerController{

    @Autowired
    private UserAnswerService userAnswerService;

    @Override
    public List<UserAnswer> getByUserIdAndFormId(Long userId, Long formId) {
        return userAnswerService.getByUserIdAndFormId(userId, formId);
    }

    @Override
    public ResponseEntity<String> submitFormAnswers(Long formId, List<UserAnswer> userAnswers) {
        boolean isSubmitted = userAnswerService.submitFormAnswers(formId, userAnswers);
        if (isSubmitted) {
            return ResponseEntity.ok("Answers submitted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit answers");
        }
    }
}
