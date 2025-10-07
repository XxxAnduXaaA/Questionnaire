package com.example.formmaker.repository;

import com.example.formmaker.entity.FormResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormResultRepository extends JpaRepository<FormResult, Long> {
    Page<FormResult> findAllByUserId(Pageable pageable, Long userId);
    Page<FormResult> findAllByForm_FormId(Pageable pageable, Long formId);
    List<FormResult> findAllByForm_FormId(Long formId);
    @Override
    Optional<FormResult> findById(Long userFormId);

    Page<FormResult> findAll(Pageable pageable);
}
