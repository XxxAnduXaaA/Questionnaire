package com.example.formmaker.controller;

import com.example.formmaker.entity.UserRanking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
public interface UserRankingController {
    @GetMapping("/{userId}/rank")
    public UserRanking getRankUserById(Long userId);
    @GetMapping("/getAllRanks")
    public List<UserRanking> getAllRanks();
}
