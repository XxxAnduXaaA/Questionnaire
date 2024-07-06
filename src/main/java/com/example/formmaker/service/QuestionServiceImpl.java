package com.example.formmaker.service;

import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.repository.FormRepository;
import com.example.formmaker.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private FormRepository formRepository;

    @Override
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionsRepository.findById(questionId);

        if(optionalQuestion.isPresent()){
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setForm(updatedQuestion.getForm());
            existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
            return questionsRepository.save(existingQuestion);
        }

        throw new RuntimeException("Question not found with id" + questionId);
    }


    @Override
    public void deleteQuestion(Long questionId) {
        questionsRepository.deleteById(questionId);
    }

    @Override
    public List<Question> findQuestionsByForm(Long formId) {
        Optional<Form> optionalForm = formRepository.findById(formId);

        if (optionalForm.isPresent()) {
            Form existingForm = optionalForm.get();


            return existingForm.getQuestions();
        }

        throw new RuntimeException("Form not found with id" + formId);
    }

//    @Override
//    public Answer addAnswerToQuestion(Long question_id, Answer answer) {
//        Optional<Question> optionalQuestion = questionsRepository.findById(question_id);
//
//        if (optionalQuestion.isPresent()) {
//            answer.setQuestion(optionalQuestion.get());
//
//            return answerRepository.save(answer);
//        }
//
//
//        throw new RuntimeException("Question not found with id" + question_id);
//    }
}
