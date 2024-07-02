package com.example.formmaker.repository;

import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRankingRepository extends JpaRepository<UserRanking, Long> {
    UserRanking findByUser(Long userId);
    List<UserRanking> findAllByOrderByRankIdAsc();
}
