package com.example.inbank_backend.service;

import com.example.inbank_backend.dto.DecisionDto;
import com.example.inbank_backend.dto.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionService {
    public static final int MINSUM = 2000;
    public static final int MAXSUM = 10000;
    public static final int MINPERIOD = 12;
    public static final int MAXPERIOD = 60;
    private final HashMap<String, Double> clientData = new HashMap<>();

    public void setClientData() {
        this.clientData.put("49002010965", 0d)
        this.clientData.put("49002010976", 100d);
        this.clientData.put("49002010987", 300d);
        this.clientData.put("49002010998", 1000d);
        this.clientData.put("49002010900", 500d);
        this.clientData.put("49002010911", 0d);
        this.clientData.put("49002010922", 200d);
        this.clientData.put("49002010933", 700d);
    }

    public DecisionDto getDecision(String code, double amount, double period) {
        setClientData();
        Double creditModifier = this.clientData.get(code);
        if (creditModifier == 0) {
            return mapDecision(LoanStatus.REJECTED, "", "", "You have a debt.");
        } else {
            return makeOffer(amount, period, creditModifier);
        }
    }

    private DecisionDto makeOffer(double amount, double period, Double creditModifier) {
        double creditScore = (creditModifier / amount) * period;
        List<String> offer = countOffer(creditModifier, amount, period);
        if (creditScore < 1d) {
            return mapDecision(LoanStatus.PROPOSITIONED, offer.get(0), offer.get(1), offer.get(2));
        } else {
            return mapDecision(LoanStatus.APPROVED, String.valueOf(amount), String.valueOf(period), offer.get(2));
        }
    }

    private List<String> countOffer(Double creditModifier, double amount, double period) {
        double offerMaxSum = creditModifier * period;
        if (offerMaxSum >= MINSUM && offerMaxSum <= MAXSUM) {
            return createOffer(period, offerMaxSum);
        } else if (offerMaxSum > MAXSUM) {
            return createOffer(period, MAXSUM);
        } else {
            return countOfferPeriod(creditModifier, amount);
        }
    }

    private List<String> countOfferPeriod(Double creditModifier, double amount) {
        double offerPeriod = 1 / (creditModifier / amount);
        if (offerPeriod >= MINPERIOD && offerPeriod <= MAXPERIOD) {
            return createOffer(offerPeriod, amount);
        } else {
            return createOffer(MAXPERIOD, creditModifier * MAXPERIOD);
        }
    }

    private List<String> createOffer(double period, double offerMaxSum) {
        List<String> offer = new ArrayList<>();
        offer.add(String.valueOf(offerMaxSum));
        offer.add(String.valueOf(period));
        offer.add(messageCreator(offerMaxSum, period));
        return offer;
    }

    public String messageCreator(double offerSum, double offerPeriod) {
        return "We can offer to you maxSum: " + offerSum + " on period " + offerPeriod + " month";
    }

    private DecisionDto mapDecision(LoanStatus status, String maxSum, String period, String explanation) {
        DecisionDto decision = new DecisionDto();
        decision.setLoanStatus(status);
        decision.setSumApproved(maxSum);
        decision.setSuitablePeriod(period);
        decision.setExplanation(explanation);
        return decision;
    }
}
