package com.example.formmaker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(max = 150)
    private String title;

    @Column
    @Size(max = 500)
    private String description;

    @Size(min = 1, message = "Должен быть хотя бы один вопрос")
    @Valid
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions;
}
