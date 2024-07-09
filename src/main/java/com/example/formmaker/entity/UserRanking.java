package com.example.formmaker.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_ranking_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
