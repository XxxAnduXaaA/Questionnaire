package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.exception.FormException;
import com.example.formmaker.repository.FormRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final UserAnswerRepository userAnswerRepository;

    public void createForm(Form form) {
        if (getFormByTitle(form.getTitle()).isPresent()) {
            throw FormException.FORM_NAME_ALREADY_TAKEN(form.getTitle());
        }

        for (Question q : form.getQuestions()) {
            q.setForm(form);
            for (Answer a : q.getAnswers()) {
                a.setQuestion(q);
            }
        }
        formRepository.save(form);
    }

    public Form createBlankForm() {
        Form form = new Form();
        Question q = new Question();
        Answer a = new Answer();

        form.setQuestions(new ArrayList<>());
        form.getQuestions().add(q);

        q.setForm(form);
        q.setAnswers((new ArrayList<>()));
        q.getAnswers().add(a);

        a.setQuestion(q);

        return form;
    }

    public List<Form> getAllForms() {
        return formRepository.findAllWithQuestions();
    }

    @Override
    public Form getFormById(Long formId) {
        return formRepository.findById(formId).orElseThrow(() -> FormException.FORM_NOT_FOUND(formId));
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
        throw FormException.FORM_NOT_FOUND(formId);
    }


    private void updateQuestions(Form existingForm, Form updatedForm) {
        List<Question> existingQuestions = existingForm.getQuestions();
        List<Question> updatedQuestions = updatedForm.getQuestions();

        existingForm.getQuestions().removeIf(q -> {
            if (!updatedQuestions.contains(q)) {
                userAnswerRepository.deleteAllByAnswerQuestionQuestionId(q.getQuestionId());

                if (q.getAnswers() != null) {
                    for (Answer a : q.getAnswers()) {
                        userAnswerRepository.deleteAllByAnswerAnswerId(a.getAnswerId());
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

    @Override
    public void deleteFormById(Long formId) {
        formRepository.deleteById(formId);
    }
}
