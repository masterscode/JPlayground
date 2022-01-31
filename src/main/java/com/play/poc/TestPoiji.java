package com.play.poc;

import com.play.models.mappings.AssessmentResult;
import com.poiji.bind.Poiji;

import java.io.File;
import java.util.List;

public class TestPoiji {

    public static void main(String[] args) {
        File excelFile = new File("/Users/decagon/IdeaProjects/JPlayground/assessment-results.xlsx");
        List<AssessmentResult> results = Poiji.fromExcel(excelFile, AssessmentResult.class);

        System.out.println(results);

        Thread.currentThread().getName();
    }
}
