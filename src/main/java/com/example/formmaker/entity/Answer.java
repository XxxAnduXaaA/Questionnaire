package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "answer_table")
@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column
    private String answerText;

    @Column
    private Boolean isCorrect;

}
