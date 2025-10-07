package com.example.formmaker.repository;

import com.example.formmaker.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    void deleteAllByQuestion_QuestionId(Long id);
}
