package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.entity.UserAnswer;
import com.example.formmaker.repository.AnswerRepository;
import com.example.formmaker.repository.FormRepository;
import com.example.formmaker.repository.QuestionsRepository;
import com.example.formmaker.repository.UserAnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Transactional
    @Override
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> optionalQuestion = questionsRepository.findById(questionId);

        if(optionalQuestion.isPresent()){
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestionText(updatedQuestion.getQuestionText());

            return questionsRepository.save(existingQuestion);
        }

        throw new RuntimeException("Question not found with id" + questionId);
    }

    @Transactional
    @Override
    public void deleteQuestion(Long questionId) {

        Question question = questionsRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + questionId));

        List<Answer> answers = question.getAnswers();

        List<UserAnswer> userAnswers = userAnswerRepository.findAllByQuestion_QuestionId(questionId);
        userAnswerRepository.deleteAll(userAnswers);

        for( Answer answer : answers){
            answerRepository.deleteById(answer.getAnswerId());
        }

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
