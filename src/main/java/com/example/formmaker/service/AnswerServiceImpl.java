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
    public Answer addAnswerToQuestion(Long question_id, Answer answer) {
        Optional<Question> optionalQuestion = questionsRepository.findById(question_id);

        if (optionalQuestion.isPresent()) {
            answer.setQuestion(optionalQuestion.get());

            return answerRepository.save(answer);
        }


        throw new RuntimeException("Question not found with id" + question_id);
    }

    @Override
    public Answer updateAnswer(Long question_id, Answer updatedAnswer) {
        Optional<Answer> optionalAnswer = answerRepository.findById(question_id);

        if (optionalAnswer.isPresent()) {
            Answer existingAnswer = optionalAnswer.get();
            existingAnswer.setAnswerText(updatedAnswer.getAnswerText());
            existingAnswer.setIsCorrect(updatedAnswer.getIsCorrect());

            return answerRepository.save(existingAnswer);
        }


        throw new RuntimeException("Question not found with id" + question_id);
    }

    @Override
    public void deleteAnswerById(Long answer_id) {
        answerRepository.deleteById(answer_id);
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long question_id) {
        return answerRepository.findByQuestion(question_id);
    }

    @Override
    public List<Answer> getCorrectAnswersByFormId(Long form_id) {
        List<Question> questions = questionsRepository.findByFormId(form_id);
        List<Answer> correctAnswers = new ArrayList<>();

        for(Question question: questions){
            correctAnswers.add(answerRepository.findAnswerByQuestionAndIsCorrectTrue(question.getQuestionId()));
        }
        return correctAnswers;
    }
}
