package com.example.formmaker.repository;

import com.example.formmaker.entity.FormResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormResultRepository extends JpaRepository<FormResult, Long> {

    @EntityGraph(attributePaths = {"form"})
    Page<FormResult> findAllByUserId(Pageable pageable, Long userId);

    Page<FormResult> findAllByFormFormId(Pageable pageable, Long formId);

    Optional<FormResult> findById(Long userFormId);

    @EntityGraph(attributePaths = {"form", "user"})
    Page<FormResult> findAll(Pageable pageable);
}
