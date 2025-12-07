package com.example.formmaker.service;

import com.example.formmaker.entity.FormResult;
import com.example.formmaker.entity.User;
import com.example.formmaker.entity.UserAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAnswerMapper {

    public UserAnswer mapToEntity(UserAnswer ua, User user, FormResult formResult) {
        ua.setUser(user);
        ua.setUserForm(formResult);
        return ua;
    }
}
