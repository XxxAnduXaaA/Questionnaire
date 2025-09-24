package com.example.formmaker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Table(name = "question_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
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
    @JsonManagedReference
    List<Answer> answers;

    @Valid
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAnswer> userAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(form.getFormId(), question.form.getFormId()) && Objects.equals(questionText, question.questionText) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(form.getFormId(), questionText, answers);
    }
}
