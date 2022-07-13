package com.play.poc.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVUtils {

//    public static <E> List<E> csvToObject(InputStream inputStream, Class<E> eClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//        List<E> result =  new ArrayList<>();
//
//        var c = eClass.forName(eClass.getName());
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
//            List<AirtimeTopup> airtimeTopupList = new ArrayList<>();
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//            for (CSVRecord csvRecord : csvRecords) {
//                AirtimeTopup airtimeTopup = new AirtimeTopup(
//                        Long.parseLong(csvRecord.get("Id")),
//                        csvRecord.get("FullName"),
//                        csvRecord.get("Phone"),
//                        csvRecord.get("Network"),
//                        Integer.parseInt(csvRecord.get("Amount")),
//                        Boolean.parseBoolean(csvRecord.get("Processed"))
//                );
//                airtimeTopupList.add(airtimeTopup);
//            }
//            return Collections.emptyList();
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }

}
