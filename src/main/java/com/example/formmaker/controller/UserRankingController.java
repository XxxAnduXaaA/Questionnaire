package com.example.formmaker.controller;

import com.example.formmaker.entity.UserRanking;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RequestMapping("/user")
public interface UserRankingController {
    @GetMapping("/{userId}/rank")
    public UserRanking getRankUserById(@PathVariable @Min(1) Long userId);
    @GetMapping("/getAllRanks")
    public List<UserRanking> getAllRanks();
}
