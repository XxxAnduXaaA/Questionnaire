package com.example.formmaker.repository;

import com.example.formmaker.entity.Form;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    Optional<Form> findByTitle(String title);

    @EntityGraph(attributePaths = {"questions"})
    @Query("SELECT f FROM Form f")
    List<Form> findAllWithQuestions();

    @EntityGraph(attributePaths = {"questions"})
    Optional<Form> findById(Long questionId);
}
