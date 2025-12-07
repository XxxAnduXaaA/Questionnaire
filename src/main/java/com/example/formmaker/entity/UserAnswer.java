package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "user_answer_table")
@Entity
@Data
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAnswerId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_form_id", nullable = false)
    private FormResult userForm;
}

