package com.play.models;

public class Transaction {
    private Account from,
            to;

    Double amount;

    ContributionCycle contributionCycle;

    private TransactionType transactionType;

    MonthlyContribution monthlyContribution;

    private enum TransactionType{
        PAYMENT, CONTRIBUTION
    }
}
