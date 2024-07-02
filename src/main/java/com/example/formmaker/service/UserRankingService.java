package com.example.formmaker.service;

import com.example.formmaker.entity.UserRanking;

import java.util.List;

public interface UserRankingService {
    public UserRanking getRankUserById(Long userId);
    public List<UserRanking> getAllRanks();
}
