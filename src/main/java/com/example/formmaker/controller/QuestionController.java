package com.example.formmaker.controller;

import com.example.formmaker.entity.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface QuestionController {
    @PutMapping("/adminPanel/question/{id}")
    public Question updateQuestion(@PathVariable @Min(1) Long id, @Valid @RequestBody Question updatedQuestion);
    @DeleteMapping("/adminPanel/question/{id}")
    public void deleteQuestion(@PathVariable @Min(1) Long id);
    @GetMapping("/adminPanel/forms/{id}/questions")
    public List<Question> findQuestionsByForm(@PathVariable @Min(1) Long id);
}
