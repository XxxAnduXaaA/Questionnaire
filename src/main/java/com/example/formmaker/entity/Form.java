package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Table(name = "form_table")
@Data
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @Column
    private String title;

    @Column
    private String description;

//    @OneToMany(mappedBy = "form")
//    @Column
//    private ArrayList<Task> tasks;

//    @Column
//    private int score = tasks.size();

    //Добавить created_by
}
