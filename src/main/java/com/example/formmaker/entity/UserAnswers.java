package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_answers")
@Entity
@Data
public class UserAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAnswerId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Questions questions;

    @ManyToOne
    @JoinColumn(name = "answerId", nullable = false)
    private Answers answers;

    @Column(nullable = false)
    private int attempt;


}

