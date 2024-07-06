package com.example.formmaker.repository;

import com.example.formmaker.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserIdAndQuestion_QuestionId(Long userId, Long questionId);
    List<UserAnswer> findByUserIdAndQuestion_Form_FormId(Long userId, Long formId);
    List<UserAnswer> findByUserId(Long userId);
}
