package com.example.formmaker.dto;

import com.example.formmaker.entity.UserAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormAnswersDto {
    private Long formId;
    private List<UserAnswer> userAnswers;
}
