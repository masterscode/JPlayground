package com.play.jSE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Applicant {
    private String name;
    private String gender;
    private String state;
    private String cycle;
    private String nyscStatus;

}
