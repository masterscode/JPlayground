package com.play.jSE;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.*;

public class RecruitmentPortal {


    public static void main(String[] args) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class cla = Applicant.class;
        var ap = new Applicant(
                "emm","em", new Date());
        for (Field field: cla.getFields()){
            field.setAccessible(true);
          map.put(
                  field.getName(), (String) field.get(ap)
          );
        }

        System.out.println(map);
    }

    public List<Applicant> extract(String path) throws FileNotFoundException, ParseException {
        var file = new FileReader("data.json");
        JSONParser parser = new JSONParser(file);

        return null;

    }


}
