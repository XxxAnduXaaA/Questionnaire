package com.example.formmaker.controller;

import com.example.formmaker.entity.Answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/adminPanel")
public interface AnswerController {
    @PostMapping("/question/{answerId}/answer")
    public Answer addAnswerToQuestion(@PathVariable @Min(1) Long answerId, @Valid @RequestBody Answer answer);
    @PutMapping("/answer/{answerId}")
    public Answer updateAnswer(@PathVariable @Min(1) Long answerId, @Valid @RequestBody Answer updatedAnswer);
    @DeleteMapping("/answer/{answerId}")
    public void deleteAnswerById(@PathVariable @Min(1) Long answerId);
    @GetMapping("/question/{answerId}/getAnswers")
    public List<Answer> getAnswersByQuestionId(@PathVariable @Min(1) Long answerId);
    @GetMapping("/question/{answerId}/getCorrectAnswer")
    public Answer getCorrectAnswerByFormId(@PathVariable @Min(1) Long answerId);
}
