package com.example.formmaker.controller;

import com.example.formmaker.entity.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/adminPanel")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public interface QuestionController {
    @PutMapping("/question/{questionId}")
    public Question updateQuestion(@PathVariable @Min(1) Long questionId, @Valid @RequestBody Question updatedQuestion);
    @DeleteMapping("/question/{questionId}")
    public void deleteQuestion(@PathVariable @Min(1) Long questionId);
    @GetMapping("/forms/{formId}/questions")
    public List<Question> findQuestionsByForm(@PathVariable @Min(1) Long formId);
}
