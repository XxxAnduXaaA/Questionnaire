package com.example.formmaker.controller;

import com.example.formmaker.entity.Answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/adminPanel")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public interface AnswerController {
    @PostMapping("/question/{questionId}/answer")
    public Answer addAnswerToQuestion(@PathVariable @Min(1) Long questionId, @Valid @RequestBody Answer answer);
    @PutMapping("/answer/{answerId}")
    public Answer updateAnswer(@PathVariable @Min(1) Long answerId, @Valid @RequestBody Answer updatedAnswer);
    @DeleteMapping("/answer/{answerId}")
    public void deleteAnswerById(@PathVariable @Min(1) Long answerId);
    @GetMapping("/question/{questionId}/getAnswers")
    public List<Answer> getAnswersByQuestionId(@PathVariable @Min(1) Long questionId);
    @GetMapping("/question/{questionId}/getCorrectAnswer")
    public Answer getCorrectAnswerByQuestionId(@PathVariable @Min(1) Long questionId);
}
