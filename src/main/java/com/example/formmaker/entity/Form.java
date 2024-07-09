package com.example.formmaker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@Table(name = "form_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long formId;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions;

    //Todo: add created_by
}
