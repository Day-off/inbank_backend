package com.example.inbank_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecisionDto {


    private LoanStatus loanStatus;
    private String sumApproved;
    private String suitablePeriod;
    private String explanation;

}
