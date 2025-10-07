//package com.example.formmaker;
//
//import com.example.formmaker.entity.*;
//import com.example.formmaker.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Random;
//
//@Component
//@RequiredArgsConstructor
//public class DataSeeder implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final FormRepository formRepository;
//    private final QuestionsRepository questionRepository;
//    private final AnswerRepository answerRepository;
//    private final FormResultRepository formResultRepository;
//    private final UserAnswerRepository userAnswerRepository;
//
//    @Override
//    public void run(String... args) {
//        Random random = new Random();
//
//        List<User> users = userRepository.findAll();
//        List<Form> forms = formRepository.findAllById(List.of(2L, 3L));
//        List<Question> questions = questionRepository.findAll();
//        List<Answer> answers = answerRepository.findAll();
//
//        for (int i = 0; i < 100; i++) {
//            User user = users.get(random.nextInt(users.size()));
//            Form form = forms.get(random.nextInt(forms.size()));
//
//            // создаём FormResult
//            FormResult formResult = new FormResult();
//            formResult.setUser(user);
//            formResult.setForm(form);
//
//            formResult = formResultRepository.save(formResult);
//
//            // под каждую форму рандомно выбираем вопрос/ответ
//            int answersCount = 1 + random.nextInt(3);
//            for (int j = 0; j < answersCount; j++) {
//                Question q = questions.get(random.nextInt(questions.size()));
//                Answer a = answers.get(random.nextInt(answers.size()));
//
//                UserAnswer ua = new UserAnswer();
//                ua.setUser(user);
//                ua.setForm(form);
//                ua.setQuestion(q);
//                ua.setAnswer(a);
//                ua.setUserForm(formResult);
//
//                userAnswerRepository.save(ua);
//            }
//        }
//    }
//}
