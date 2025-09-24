package com.example.formmaker.repository;

import com.example.formmaker.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findAllByQuestion_Form_FormId(Long formId);
    void deleteAllByQuestion_QuestionId(Long questionId);
    void deleteAllByAnswer_AnswerId(Long id);
}
