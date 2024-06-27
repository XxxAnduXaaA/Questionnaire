package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "questions")
@Data
@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "formId", nullable = false)
    private Form formId;

    @Column
    private String questionText;
}
