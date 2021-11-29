package com.play.poc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssessmentResult {
    private String qualifiedID;
    private String candidateID;
    private String candidateName;
    private String candidateEmail;
    private Boolean isTeamMember;
    private String assessmentTitle;
    private double score;
    private String state;
    private boolean isInteractSession;
    private String createdAt;
    private String startedAt;
    private String submittedAt;
    private String updatedAt;
    private String candidateRemarks;
}
