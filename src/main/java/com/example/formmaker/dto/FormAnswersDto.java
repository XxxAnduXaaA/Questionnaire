package com.example.formmaker.dto;

import com.example.formmaker.entity.UserAnswer;
import lombok.Data;

import java.util.List;

@Data
public class FormAnswersDto {
    private Long formId;
    private List<UserAnswer> userAnswers;
}