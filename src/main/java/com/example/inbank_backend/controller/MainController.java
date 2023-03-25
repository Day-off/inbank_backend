package com.example.inbank_backend.controller;


import com.example.inbank_backend.dto.DecisionDto;
import com.example.inbank_backend.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {

    private final DecisionService decisionService;

    @GetMapping(value = "getDecision")
    public DecisionDto getDecision(String code, double amount, double period) {
        return decisionService.getDecision(code, amount, period);
    }

}
