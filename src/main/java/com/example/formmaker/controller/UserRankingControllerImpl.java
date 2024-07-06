package com.example.formmaker.controller;

import com.example.formmaker.entity.UserRanking;
import com.example.formmaker.service.UserRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRankingControllerImpl implements UserRankingController {

    @Autowired
    private UserRankingService userRankingService;

    @Override
    public UserRanking getRankUserById(Long userId) {
        return userRankingService.getRankUserById(userId);
    }

    @Override
    public List<UserRanking> getAllRanks() {
        return userRankingService.getAllRanks();
    }
}
