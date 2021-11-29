package com.play.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asssessment {
    public static void main(String[] args) throws IOException {
        FileInputStream assessmentResults = new FileInputStream(new File("assessment-results.xlsx"));

        Workbook assessmentWorkBook = new XSSFWorkbook(assessmentResults);
        Map<String, Map<String, String>> data = new HashMap<>();
        List<AssessmentResult> assessmentResultList = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        Sheet resultSheet = assessmentWorkBook.getSheetAt(0);
        List<String> finalKeys = keys;
        resultSheet.getRow(0).forEach(cell -> finalKeys.add(cell.getStringCellValue()));

        keys = keys.stream().filter(k->!k.isBlank()).toList();



//       for (int i =0; i <= 10; i++){
//
//           data.put("Row - " + i, new ArrayList<>());
//           var resultSheetRow = resultSheet.getRow(i);
////           resultSheetRow.
//           for (Cell cell : resultSheetRow){
//               var type = cell.getCellTypeEnum();
//               if (type == CellType.STRING) data.get("Row - " + i).put(keys.get(i),cell.getStringCellValue());
//               if (type == CellType.BOOLEAN) data.get("Row - " + i).put(keys.get(i),String.valueOf(cell.getBooleanCellValue()));
//               if (type == CellType.NUMERIC) data.get("Row - " + i).put(keys.get(i),String.valueOf(cell.getNumericCellValue()));
//           }
//
//       }
//       assessmentWorkBook.close();
//       assessmentResults.close();

        System.out.println(keys);


//        data.keySet().stream().skip(1).forEach(key->{
//            var result = new AssessmentResult();
//            var row = data.get(key);
//            int i = 0;
//            result.setQualifiedID(row.get(i++));
//            result.setCandidateID(row.get(i++));
//            result.setCandidateName(row.get(i++));
//            result.setCandidateEmail(row.get(i++));
//            result.setIsTeamMember((row.get(i++).equalsIgnoreCase("false")));
//            result.setAssessmentTitle(row.get(i++));
//            result.setScore(Double.parseDouble(row.get(i++)));
//            result.setState(row.get(i++));
//            result.setInteractSession((row.get(i++).equalsIgnoreCase("false")));
//            result.setCreatedAt(row.get(i++));
//            result.setStartedAt(row.get(i++));
//            result.setSubmittedAt(row.get(i++));
//            result.setUpdatedAt(row.get(i++));
//            result.setCandidateRemarks(row.get(i - 1));
//            assessmentResultList.add(result);
//        });


//        assessmentResultList.forEach(System.out::println);
    }

    public static  void anotherWay() throws FileNotFoundException, IOException {
        FileInputStream assessmentResults = new FileInputStream(new File("assessment-results.xlsx"));
        ObjectMapper objectMapper = new ObjectMapper();

        Workbook assessmentWorkBook = new XSSFWorkbook(assessmentResults);
        Map<String, Map<String, String>> data = new HashMap<>();
        List<AssessmentResult> assessmentResultList = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        Sheet resultSheet = assessmentWorkBook.getSheetAt(0);
        List<String> finalKeys = keys;
        resultSheet.getRow(0).forEach(cell -> finalKeys.add(cell.getStringCellValue()));

        keys = keys.stream().filter(k->!k.isBlank()).toList();


//        for (int i =0; i <= 10; i++){
//
//            data.put("Row - " + i, new ArrayList<>());
//            var resultSheetRow = resultSheet.getRow(i);
////           resultSheetRow.
//            for (Cell cell : resultSheetRow){
//                var type = cell.getCellTypeEnum();
//                if (type == CellType.STRING) data.get("Row - " + i).put(keys.get(i),cell.getStringCellValue());
//                if (type == CellType.BOOLEAN) data.get("Row - " + i).put(keys.get(i),String.valueOf(cell.getBooleanCellValue()));
//                if (type == CellType.NUMERIC) data.get("Row - " + i).put(keys.get(i),String.valueOf(cell.getNumericCellValue()));
//            }
//
//        }
//        assessmentWorkBook.close();
//        assessmentResults.close();
//
//        System.out.println(keys);


    }


}
