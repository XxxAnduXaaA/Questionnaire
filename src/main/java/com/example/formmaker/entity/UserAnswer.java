package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_form_id", nullable = false)
    private FormResult userForm;

    @Override
    public String toString() {
        return "UserAnswer{" +
                "userAnswerId=" + userAnswerId +
                ", user=" + user +
                ", form=" + form +
                ", question=" + question +
                ", answer=" + answer +
                ", userForm=" + userForm +
                '}';
    }
}

