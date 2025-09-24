package com.example.formmaker.service;

import com.example.formmaker.entity.*;
import com.example.formmaker.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final FormResultRepository formResultRepository;

    public FormServiceImpl(FormRepository formRepository, UserAnswerRepository userAnswerRepository, FormResultRepository formResultRepository) {
        this.formRepository = formRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.formResultRepository = formResultRepository;
    }

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form getFormById(Long formId) {
        return formRepository.findById(formId).orElse(null);
    }

    public Optional<Form> getFormByTitle(String title) {
        return formRepository.findByTitle(title);
    }

    @Transactional
    @Override
    public Form updateFormById(Long formId, Form updatedForm) {
        Optional<Form> optionalForm = formRepository.findById(formId);

        if (optionalForm.isPresent()) {
            Form existingForm = optionalForm.get();
            existingForm.setDescription(updatedForm.getDescription());
            existingForm.setTitle(updatedForm.getTitle());

            // Устанавливаем обратные ссылки для новых вопросов и ответов
            for (Question updatedQ : updatedForm.getQuestions()) {
                updatedQ.setForm(existingForm);
                if (updatedQ.getAnswers() != null) {
                    for (Answer updatedA : updatedQ.getAnswers()) {
                        updatedA.setQuestion(updatedQ);
                    }
                }
            }
            updateQuestions(existingForm, updatedForm);
            return formRepository.save(existingForm);
        }
        throw new RuntimeException("Form not found with id" + formId);
    }


    private void updateQuestions(Form existingForm, Form updatedForm) {
        List<Question> existingQuestions = existingForm.getQuestions();
        List<Question> updatedQuestions = updatedForm.getQuestions();

        existingForm.getQuestions().removeIf(q -> {
            if (!updatedQuestions.contains(q)) {
                userAnswerRepository.deleteAllByQuestion_QuestionId(q.getQuestionId());

                if (q.getAnswers() != null) {
                    for (Answer a : q.getAnswers()) {
                        userAnswerRepository.deleteAllByAnswer_AnswerId(a.getAnswerId());
                    }
                }
                return true; // удаляем вопрос из коллекции
            }
            return false;
        });

        for (Question updatedQ : updatedQuestions) {
            if (!existingQuestions.contains(updatedQ)) {
                existingQuestions.add(updatedQ);
            }
        }
    }

    @Transactional
    @Override
    public void deleteFormById(Long formId) {
        List<UserAnswer> userAnswers = userAnswerRepository.findAllByQuestion_Form_FormId(formId);
        userAnswerRepository.deleteAll(userAnswers);
        List<FormResult> formResults = formResultRepository.findAllByForm_FormId(formId);
        formResultRepository.deleteAll(formResults);
        formRepository.deleteById(formId);
    }
}
