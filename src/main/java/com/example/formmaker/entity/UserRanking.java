package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_ranking_table")
@Data
@Entity
public class UserRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long rankingId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private double totalScore;
}
