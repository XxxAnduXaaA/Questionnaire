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

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", formId=" + (form != null ? form.getFormId() : null) +
                ", questionText='" + questionText + '\'' +
                ", answers=" + (answers != null ? answers.size() : 0) +
                '}';
    }


    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    @JsonBackReference
    private Form form;

    @Column
    private String questionText;

    @Valid
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(
    List<Answer> answers;
}
