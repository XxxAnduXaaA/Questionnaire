package com.example.formmaker.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormResult {

    @Id
    @GeneratedValue
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


