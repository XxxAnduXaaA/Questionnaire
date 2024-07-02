package com.example.formmaker.repository;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Long questionId);
    Answer findAnswerByQuestionAndIsCorrectTrue(Long questionId);
}
