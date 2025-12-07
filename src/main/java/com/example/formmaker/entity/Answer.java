package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name = "answer_table")
@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false)
    private String answerText;
}
