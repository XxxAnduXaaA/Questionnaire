package com.example.formmaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "question_table")
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    @JsonBackReference
    private Form form;

    @Column
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Answer> answers;
}
