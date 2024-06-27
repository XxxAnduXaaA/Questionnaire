package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_ranking")
@Data
@Entity
public class UserRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long rankingId;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column
    private double totalScore;
}
