package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class FormResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    private User user;

    @NotNull
    @ManyToOne(optional = false)
    private Form form;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime completedAt;

    @Size(min = 1)
    @Valid
    @OneToMany(mappedBy = "userForm", cascade = CascadeType.ALL)
    private List<UserAnswer> answers;

    @PrePersist
    public void onPrePersist(){
        this.completedAt = LocalDateTime.now();
    }

}


