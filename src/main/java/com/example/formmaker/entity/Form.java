package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@Table(name = "form_table")
@Data
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long form_id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "form_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

//    @Column
//    private int score = tasks.size();

    //Добавить created_by
}
