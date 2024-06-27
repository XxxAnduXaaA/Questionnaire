package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

}
