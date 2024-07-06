package com.example.formmaker.service;

import com.example.formmaker.entity.*;
import com.example.formmaker.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService{

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private UserRankingRepository userRankingRepository;

    public int scoreCounter(Long userId, Long formId){
        List<UserAnswer> userAnswers = userAnswerRepository.findByUserIdAndQuestion_Form_FormId(userId, formId);
        int score = 0;

        if (!userAnswers.isEmpty()){
            for(UserAnswer userAnswer : userAnswers){
                Answer correctAnswer = answerRepository.findAnswerByQuestion_QuestionIdAndIsCorrectTrue(userAnswer.getQuestion().getQuestionId());
                if(userAnswer.getAnswer().getAnswerId().equals(correctAnswer.getAnswerId())){
                    score++;
                }
            }
        }

        return score;
    }


    @Override
    public List<UserAnswer> getAnswerByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.findByUserIdAndQuestion_QuestionId(userId, questionId);
    }

    @Override
    public List<UserAnswer> getByUserIdAndFormId(Long userId, Long formId) {
        return userAnswerRepository.findByUserIdAndQuestion_Form_FormId(userId, formId);
    }

    @Override
    public List<UserAnswer> getByUserId(Long userId) {
        return userAnswerRepository.findByUserId(userId);
    }

    @Override
    public boolean submitFormAnswers(Long formId, List<UserAnswer> userAnswers) {

        // Проверяем, что форма существует
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException("Form not found with id: " + formId));



        for (UserAnswer userAnswer : userAnswers) {
            Question question = questionsRepository.findById(userAnswer.getQuestion().getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + userAnswer.getQuestion().getQuestionId()));

            // Проверяем, что вопрос принадлежит форме
            if (!question.getForm().equals(formId)) {
                throw new IllegalArgumentException("Question does not belong to the form with id: " + formId);
            }

            Answer correctAnswer = answerRepository.findAnswerByQuestion_QuestionIdAndIsCorrectTrue(question.getQuestionId());

            User user = userRepository.findById(userAnswer.getUser().getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userAnswer.getUser().getId()));

            Answer answer = answerRepository.findById(userAnswer.getAnswer().getAnswerId())
                    .orElseThrow(() -> new EntityNotFoundException("Answer not found with id: " + userAnswer.getAnswer().getAnswerId()));

            userAnswer.setUser(user);
            userAnswer.setQuestion(question);
            userAnswer.setAnswer(answer);

            userAnswerRepository.save(userAnswer);
        }
        Long userId = userAnswers.get(0).getUserAnswerId();

        int score = scoreCounter(userId,formId);

        // Обновляем общий балл пользователя
        UserRanking userRanking = userRankingRepository.findByUserId(userId);
        if (userRanking != null) {
            userRanking.setTotalScore(userRanking.getTotalScore() + score);
            userRankingRepository.save(userRanking);
        } else {
            userRanking = new UserRanking();
            userRanking.setUser(userAnswers.get(0).getUser());
            userRanking.setTotalScore(score);
            userRankingRepository.save(userRanking);
        }

        return true;
    }


}
