package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Table(name = "question_table")
@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    private String questionText;

    @Valid
    @OneToMany(
            mappedBy = "question",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true
    )
    List<Answer> answers;
}
