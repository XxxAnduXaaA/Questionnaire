package com.example.formmaker.service;

import com.example.formmaker.entity.UserRanking;
import com.example.formmaker.repository.UserRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRankingServiceImpl implements UserRankingService{

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
