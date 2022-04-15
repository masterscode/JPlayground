package com.play.jSE;

import org.apache.commons.collections4.map.HashedMap;

import javax.validation.constraints.NotNull;
import java.text.DateFormatSymbols;
import java.util.*;

public class Mnth {


    public static void main(String[] args) {
        Mnth mnth = new Mnth();

        mnth.addToRegistry(1L, new Date(), "First");
        mnth.addToRegistry(1L, new Date(), "First");
        mnth.addToRegistry(4L, new Date(), "First");
        mnth.addToRegistry(1L, new Date(), "Third");
        mnth.addToRegistry(2L, new Date(), "First");
        mnth.addToRegistry(2L, new Date(), "Second");
        mnth.addToRegistry(1L, new Date(), "First");
        mnth.addToRegistry(1L, new Date(), "Fourth");
        mnth.addToRegistry(2L, new Date(), "Third");

        System.out.println(
                mnth.getFromRegistry(1L, "First")
        );
    }

    private final Map<Long, Map<String, Map<String, Integer>>> registry = new HashMap<>();


    void addToRegistry(@NotNull Long orgId, @NotNull Date date, @NotNull String period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final String[] allMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();

        String monthName = allMonths[calendar.get(Calendar.MONTH)];
        Map<String, Map<String, Integer>> orgRegistry = registry.computeIfAbsent(orgId, k -> new HashMap<>());
        Map<String, Integer> periodRegistry = orgRegistry.computeIfAbsent(period, p -> new HashMap<String, Integer>());
        int count = periodRegistry.getOrDefault(monthName, 0);
        periodRegistry.put(monthName, ++count);
    }

    Map<String, Integer> getFromRegistry(Long orgId, String period) {

        if (orgId != null && period != null) {
            return registry.get(orgId).get(period);
        }

        if (orgId != null) {
            Map<String, Integer> allPeriodsResult = new HashMap<>();
            var orgRegistry = registry.get(orgId);

            orgRegistry.values().forEach(keyValue -> {
                keyValue.keySet().forEach(key -> {
                    int count = allPeriodsResult.getOrDefault(key, 0);
                    allPeriodsResult.put(key, count + keyValue.get(key));
                });
            });

            return allPeriodsResult;
        }


        if (period != null) {
            Map<String, Integer> specificPeriod = new HashMap<>();
            registry.values()
                    .stream()
                    .filter(periods -> periods.get(period) != null)
                    .map(availablePeriods -> availablePeriods.get(period))

                    .forEach(monthMap -> {
                        monthMap.keySet().forEach(month -> {
                            int count = specificPeriod.getOrDefault(month, 0);
                            specificPeriod.put(month, count + monthMap.get(month));
                        });
                    });

            return specificPeriod;
        }

        Map<String, Integer> result = new HashMap<>();

        for (var entry : registry.values()) {
            var monthValue = entry.values();
            for (var month : monthValue) {
                month.keySet().forEach(k -> {
                    int count = result.getOrDefault(k, 0);
                    result.put(k, count + month.get(k));
                });
            }
        }
        return result;

    }


    public static Map<String, Integer> addKeys(Map<String, Integer>... maps) {
        Set<String> keys = new HashSet<String>();
        for (Map<String, Integer> map : maps)
            keys.addAll(map.keySet());
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String key : keys) {
            Integer value = 0;
            for (Map<String, Integer> map : maps)
                if (map.containsKey(key))
                    value += map.get(key);
            result.put(key, value);
        }
        return result;
    }

//    void addToRegistry(@NotNull Map<Long, Map<String, Map<String, Integer>>> registry, @NotNull Long orgId, @NotNull Date date, @NotNull String period) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        final String[] allMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();
//
//        String monthName = allMonths[calendar.get(Calendar.MONTH)];
//        Map<String, Map<String, Integer>> orgRegistry = registry.computeIfAbsent(orgId, k -> new HashMap<>());
//        Map<String, Integer> periodRegistry = orgRegistry.computeIfAbsent(period, p -> new HashMap<String, Integer>());
//        int count = periodRegistry.getOrDefault(monthName, 0);
//        periodRegistry.put(monthName, ++count);
//
//    }
//
//
//    Map<String, Integer> getFromRegistry(@NotNull Map<Long, Map<String, Map<String, Integer>>> registry, Long orgId, String period) {
//
//        if (orgId != null && period != null) {
//            return registry.get(orgId).get(period);
//        }
//
//        if (orgId != null) {
//            Map<String, Integer> allPeriodsResult = new HashMap<>();
//            var orgRegistry = registry.get(orgId);
//
//            orgRegistry.values().forEach(keyValue -> {
//                keyValue.keySet().forEach(key -> {
//                    int count = allPeriodsResult.getOrDefault(key, 0);
//                    allPeriodsResult.put(key, count + keyValue.get(key));
//                });
//            });
//
//            return allPeriodsResult;
//        }
//
//
//        if (period != null) {
//            Map<String, Integer> specificPeriod = new HashMap<>();
//            registry.values()
//                    .stream()
//                    .map(periods -> periods.get(period))
//                    .forEach(key -> {
//                        key.keySet().forEach(k -> {
//                            int count = specificPeriod.getOrDefault(k, 0);
//                            specificPeriod.put(k, count + key.get(k));
//                        });
//                    });
//
//            return specificPeriod;
//        }
//
//        Map<String, Integer> result = new HashMap<>();
//
//        for (var entry : registry.values()) {
//            var monthValue = entry.values();
//            for (var month : monthValue) {
//                month.keySet().forEach(k -> {
//                    int count = result.getOrDefault(k, 0);
//                    result.put(k, count + month.get(k));
//                });
//            }
//        }
//        return result;
//
//    }
//
//
//    private Map<String, Integer> getMonthInfo(Map<Long, Map<String,Map<String,Integer>>> monthYearCountMap, long organizationId, String period ){
//        Map<String, Integer> result = new HashedMap();
//        Map<String, Map<String, Integer>> monthMap = monthYearCountMap.get(organizationId);
//        Map<String, Integer> months;
//        for (Map.Entry<String, Map<String, Integer>> map: monthMap.entrySet()) {
//            months = map.getValue();
//            result.putAll(months);
//        }
//        System.out.println("REUSLT " + result);
//        return result;
//    }
//


//    private void addToRegistry(@NotNull Map<Long, Map<String, Map<String, Integer>>> registry, @NotNull Long organizationId, @NotNull Date date, @NotNull String period) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        final String[] allMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();
//
//        String monthName = allMonths[calendar.get(Calendar.MONTH)];
//
//        Map<String, Map<String, Integer>> periodCountMap = registry.computeIfAbsent(organizationId, k -> new HashMap<>());
//        Map<String, Integer> months = periodCountMap.computeIfAbsent(period, k -> new HashMap<>());
//
//        int count = months.getOrDefault(monthName, 0);
//        months.put(monthName, ++count);
//    }
//
//    private Map<String, Integer> getFromRegistry(@NotNull Map<Long, Map<String, Map<String, Integer>>> registry, Long organizationId, String period) {
//        Map<String, Integer> result = new HashedMap();
//
//        Map<String, Integer> months;
//
//        if (organizationId == null) {
//            for (var outerMostMap : registry.entrySet()) {
//                var innerMap = outerMostMap.getValue();
//                for (var innerMostMap : innerMap.entrySet()) {
//                    if (period == null){
//                        var map = innerMostMap.getValue();
//                        for (var mainMap : map.entrySet())
//                            if (result.containsKey(mainMap.getKey())) {
//                                result.put(mainMap.getKey(), result.get(mainMap.getKey()) + mainMap.getValue());
//                            } else {
//                                result.put(mainMap.getKey(), mainMap.getValue());
//                            }
//                    }
//                    if (innerMostMap.getKey() == period) {
//                        var map = innerMostMap.getValue();
//                        for (var mainMap : map.entrySet()){
//                            if (result.containsKey(mainMap.getKey())) {
//                                result.put(mainMap.getKey(), result.get(mainMap.getKey()) + mainMap.getValue());
//                            } else {
//                                result.put(mainMap.getKey(), mainMap.getValue());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (organizationId != null) {
//            var monthMap = registry.get(organizationId);
//            if (period ==  null) {
//                for (var map : monthMap.entrySet()) {
//                    months = map.getValue();
//                    result.putAll(months);
//                }
//            } else {
//                var periodMonth = monthMap.get(period);
//                result.putAll(periodMonth);
//            }
//        }
//        System.out.println("RESULT " + result);
//        return result;
//    }

}
