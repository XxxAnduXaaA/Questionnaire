package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_answer_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userAnswerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(nullable = false)
    private int attempt;


}

