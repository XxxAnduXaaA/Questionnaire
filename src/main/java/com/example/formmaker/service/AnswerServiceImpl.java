package com.example.formmaker.service;

import com.example.formmaker.entity.Answer;
import com.example.formmaker.entity.Form;
import com.example.formmaker.entity.Question;
import com.example.formmaker.repository.AnswerRepository;
import com.example.formmaker.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Answer addAnswerToQuestion(Long questionId, Answer answer) {
        Optional<Question> optionalQuestion = questionsRepository.findById(questionId);

        if (optionalQuestion.isPresent()) {
            answer.setQuestion(optionalQuestion.get());

            return answerRepository.save(answer);
        }


        throw new RuntimeException("Question not found with id" + questionId);
    }

    @Override
    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        if (optionalAnswer.isPresent()) {
            Answer existingAnswer = optionalAnswer.get();
            existingAnswer.setAnswerText(updatedAnswer.getAnswerText());
            existingAnswer.setIsCorrect(updatedAnswer.getIsCorrect());

            return answerRepository.save(existingAnswer);
        }


        throw new RuntimeException("Answer not found with id" + answerId);
    }

    @Override
    public void deleteAnswerById(Long answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestion(questionId);
    }

    @Override
    public Answer getCorrectAnswerByQuestionId(Long questionId) {
        return answerRepository.findAnswerByQuestionAndIsCorrectTrue(questionId);
    }
}
