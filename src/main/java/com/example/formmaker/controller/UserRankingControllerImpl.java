package com.example.formmaker.controller;

import com.example.formmaker.entity.UserRanking;
import com.example.formmaker.repository.UserRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRankingControllerImpl implements UserRankingController {

    @Autowired
    private UserRankingRepository userRankingRepository;

    @Override
    public UserRanking getRankUserById(Long userId) {
        return userRankingRepository.findByUser(userId);
    }

    @Override
    public List<UserRanking> getAllRanks() {
        return userRankingRepository.findAllByOrderByRankIdAsc();
    }
}
