package com.example.formmaker.repository;

import com.example.formmaker.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {

    Question findByAnswers_AnswerId(Long answerId);
}
