package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "answers")
@Data
@Entity
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Questions questions;

    @Column
    private String answerText;

    @Column
    private Boolean isCorrect;

}
