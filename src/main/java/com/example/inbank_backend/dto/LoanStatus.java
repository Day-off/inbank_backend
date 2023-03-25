package com.example.inbank_backend.dto;

import lombok.Getter;

@Getter
public enum LoanStatus {

    APPROVED("Approved"),
    REJECTED("Rejected"),
    PROPOSITIONED("Propositioned");

    private final String value;

    LoanStatus(String value) {
        this.value = value;
    }
}
