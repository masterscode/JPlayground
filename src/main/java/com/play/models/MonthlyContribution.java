package com.play.models;

import java.time.LocalDate;
import java.util.List;

public class MonthlyContribution {
    private LocalDate paymentDay;

    private LocalDate contributionStartDay,
            contributionEndDay;

    private Double contributedAmount;

    private User beneficiary;

    private List<User> contributors;

    private ContributionCycle contributionCycle;
}
