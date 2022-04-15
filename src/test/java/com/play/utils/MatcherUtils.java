package com.play.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MatcherUtils {

    @EqualsAndHashCode(callSuper = true)
    @Slf4j
    @AllArgsConstructor
    @Data
    public static class IntMatcher extends BaseMatcher<Integer> {
        private int v;

        @Override
        public boolean matches(Object actual) {
            try {
                int i = (int) actual;
            } catch (Exception e) {
                return false;
            }

            return v == (int) actual;
        }

        @Override
        public void describeMismatch(Object actual, Description mismatchDescription) {
            super.describeMismatch(actual, mismatchDescription);
        }

        @Override
        public void describeTo(Description description) {
            log.info(description.toString());
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Slf4j
    @AllArgsConstructor
    @Data
    public static class StringMatcher extends BaseMatcher<String> {
        private String v;

        @Override
        public boolean matches(Object actual) {

            try {
                String i = (String) actual;
            } catch (Exception e) {
                return false;
            }

            return v.equals(actual);
        }

        @Override
        public void describeMismatch(Object actual, Description mismatchDescription) {
            super.describeMismatch(actual, mismatchDescription);
        }


        @Override
        public void describeTo(Description description) {

        }
    }

    private void addToRegistry(Map<String, Map<String, Map<String, Integer>>> registry, Date date, String placementType, String period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final String[] allMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();

        String monthName = allMonths[calendar.get(Calendar.MONTH)];

        Map<String, Map<String, Integer>> periodRegistry = registry.computeIfAbsent(period, k -> new HashMap<>());

        Map<String, Integer> monthRegistry = periodRegistry.computeIfAbsent(monthName, p -> new HashMap<String, Integer>());
        int count = monthRegistry.getOrDefault(placementType, 0) + 1;
        monthRegistry.put(placementType, count);
    }

    private Map<String, Object> getFromRegistry(Map<String, Map<String, Map<String, Integer>>> registry, String period) {
        Map<String, Object> result = new HashMap<>();

        if (period == null) {
            //add all months to monthList
            result.put("months", registry.values().stream().flatMap(col -> col.keySet().stream()).toList().toArray(new String[0]));


            //add all placementType to placementTypes


            //add all counts to counts
            result.put("counts",
                    registry.values().stream().flatMap(col -> col.values().stream().flatMap(count -> count.values().stream())).toArray(Integer[]::new));


            return result;
        }
//
//
        //add period months
        result.put("months", new ArrayList<>(registry.get(period).keySet()).toArray(new String[0]));

        //add period placementType
        result.put("placements", registry.get(period).values().stream().flatMap(col -> col.keySet().stream()).collect(Collectors.toList()).toArray(new String[0]));


        //add period counts
        result.put("counts", registry.get(period).values().stream().flatMap(col -> col.values().stream()).collect(Collectors.toList()).toArray(new Integer[0]));


        return result;

    }

}
