package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.repository.FormRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FormServiceImpl implements FormService{

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public Form createForm(Form form){
        return formRepository.save(form);
    }

    public List<Form> getAllForms(){
        return formRepository.findAll();
    }

    @Override
    public Form getFormById(Long formId) {
        return formRepository.findById(formId).orElse(null);
    }

    @Override
    public Form updateFormById(Long formId, Form updatedForm) {
        Optional<Form> optionalForm = formRepository.findById(formId);

        if(optionalForm.isPresent()){
            Form existingForm = optionalForm.get();
            existingForm.setDescription(updatedForm.getDescription());
            existingForm.setTitle(updatedForm.getTitle());

            return formRepository.save(existingForm);
        }

            throw new RuntimeException("Form not found with id" + formId);
    }

    @Override
    public Form addQuestionToForm(Long formId, Question question) {
        Optional<Form> optionalForm = formRepository.findById(formId);

        if(optionalForm.isPresent()){
            Form existingForm = optionalForm.get();
            existingForm.getQuestions().add(question);
            question.setForm(existingForm);

            return formRepository.save(existingForm);
        }

        throw new RuntimeException("Form not found with id" + formId);
    }

    @Transactional
    @Override
    public void deleteFormById(Long formId) {
        List<UserAnswer> userAnswers = userAnswerRepository.findAllByQuestion_Form_FormId(formId);
        userAnswerRepository.deleteAll(userAnswers);
        formRepository.deleteById(formId);
    }
}
