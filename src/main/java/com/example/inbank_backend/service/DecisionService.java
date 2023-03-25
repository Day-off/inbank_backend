package com.example.inbank_backend.service;

import com.example.inbank_backend.dto.DecisionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DecisionService {


    private final HashMap<String, Double> clientData = new HashMap<>();

    public void setClientData() {
        this.clientData.put("49002010965", 0d);
        this.clientData.put("49002010976", 100d);
        this.clientData.put("49002010987", 300d);
        this.clientData.put("49002010998", 1000d);
        this.clientData.put("49002010900", 500d);
        this.clientData.put("49002010911", 0d);
        this.clientData.put("49002010922", 200d);
        this.clientData.put("49002010933", 700d);
    }

    public DecisionDto getDecision(String code, double amount, double period) {
        return new DecisionDto();
    }
}
