package com.play.models;

import java.time.LocalDate;
import java.util.List;

public class ContributionCycle {
    private String title;

    private LocalDate startDate, endDate;

    private List<User> users;

    private List<User> beneficiaries;

    private Double contributionAmount;

    private Boolean isClosed;

    private Double totalAmountContributed;

    private Account account;

    List<MonthlyContribution> monthlyContributions;

}
