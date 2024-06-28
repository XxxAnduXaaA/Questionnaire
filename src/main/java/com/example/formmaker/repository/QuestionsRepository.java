package com.example.formmaker.repository;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {
    List<Question> findByForm(Form form);
}
