package com.example.formmaker.controller;

import com.example.formmaker.entity.UserAnswer;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserAnswerController {
//    @GetMapping("/user/{userId}/")
//    public UserAnswer getAnswerByUserIdAndQuestionId(Long userId, Long questionId);
    @GetMapping("/user/{userId}/completedForm/{formId}")
    public List<UserAnswer> getByUserIdAndFormId(@PathVariable @Min(1) Long userId, @PathVariable @Min(1) Long formId);
    @PostMapping("/form/{formId}/submit-form-answers")
    public ResponseEntity<String> submitFormAnswers(@PathVariable @Min(1) Long formId, @RequestBody List<UserAnswer> userAnswers);

}
