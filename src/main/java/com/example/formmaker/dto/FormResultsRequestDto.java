package com.example.formmaker.dto;

import lombok.Data;

@Data
public class FormResultsRequestDto {
    Long formId;
    Long userId;
    int page = 0;
    int size = 10;
}
