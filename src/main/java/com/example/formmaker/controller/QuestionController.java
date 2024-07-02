package com.example.formmaker.controller;

import com.example.formmaker.entity.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface QuestionController {
    @PutMapping("/adminPanel/question/{questionId}")
    public Question updateQuestion(@PathVariable @Min(1) Long questionId, @Valid @RequestBody Question updatedQuestion);
    @DeleteMapping("/adminPanel/question/{questionId}")
    public void deleteQuestion(@PathVariable @Min(1) Long questionId);
    @GetMapping("/adminPanel/forms/{questionId}/questions")
    public List<Question> findQuestionsByForm(@PathVariable @Min(1) Long questionId);
}
