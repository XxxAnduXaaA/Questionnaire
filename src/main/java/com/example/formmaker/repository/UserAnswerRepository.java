package com.example.formmaker.repository;

import com.example.formmaker.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserIdAndQuestionId(Long userId, Long questionId);
    List<UserAnswer> findByUserIdAndFormId(Long userId, Long quizId);
    List<UserAnswer> findByUserId(Long userId);
}
