package com.play.models.mappings;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AssessmentResult {
    @ExcelRow
    private int rowIndex;
    @ExcelCellName("Candidate ID")
    private String candidateId;
    @ExcelCellName("candidateName")
    private String name;
    @ExcelCellName("candidateEmail")
    private String email;
    @ExcelCellName("Team Member?")
    private boolean isTeamMember;
    @ExcelCellName("assessment title")
    private String assessmentTitle;
    @ExcelCellName("score")
    private int score;
    @ExcelCellName("state")
    private String state;
}
