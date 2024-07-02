package com.example.formmaker.controller;

import com.example.formmaker.entity.Answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AnswerController {
    @PostMapping("/adminPanel/question/{answerId}/answer")
    public Answer addAnswerToQuestion(@PathVariable @Min(1) Long answerId, @Valid @RequestBody Answer answer);
    @PutMapping("/adminPanel/answer/{answerId}")
    public Answer updateAnswer(@PathVariable @Min(1) Long answerId, @Valid @RequestBody Answer updatedAnswer);
    @DeleteMapping("/adminPanel/answer/{answerId}")
    public void deleteAnswerById(@PathVariable @Min(1) Long answerId);
    @GetMapping("/adminPanel/question/{answerId}/getAnswers")
    public List<Answer> getAnswersByQuestionId(@PathVariable @Min(1) Long answerId);
    @GetMapping("/adminPanel/question/{answerId}/getCorrectAnswer")
    public Answer getCorrectAnswerByFormId(@PathVariable @Min(1) Long answerId);
}
