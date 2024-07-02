package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "question_table")
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form formId;

    @Column
    private String questionText;
}
