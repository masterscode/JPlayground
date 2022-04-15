package com.play.jSE;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class EngineerAssignment {

    public static void main(String[] args) {
        final Map<String, Map<String, Map<String, Integer>>> registry = new HashMap<>();

        EngineerAssignment assignment = new EngineerAssignment();

        assignment.addToRegistry(registry, new Date(), "INTERNSHIP", "THIS_MONTH");
        assignment.addToRegistry(registry, new Date(), "INTERVIEW", "THIS_MONTH");
        assignment.addToRegistry(registry, new Date(), "RESIDENCE", "THIS_MONTH");
        assignment.addToRegistry(registry, new Date(), "INTERNSHIP", "TODAY");
        assignment.addToRegistry(registry, new Date(), "INTERVIEW", "THIS_MONTH");
        assignment.addToRegistry(registry, new Date(), "INTERNSHIP", "7DAYSAGO");
        assignment.addToRegistry(registry, new Date(), "RESIDENCE", "THIS_MONTH");
        assignment.addToRegistry(registry, new Date(), "RESIDENCE", "LAST3MONTHS");
        assignment.addToRegistry(registry, new Date(), "INTERVIEW", "TODAY");

        System.out.println(
                registry
        );
        System.out.println(
                assignment.getFromRegistry(registry, "THIS_MONTH")
        );
    }



    private void addToRegistry(Map<String,Map<String, Map<String, Integer>>> registry, Date date, String placementType, String period){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final String[] allMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();

        String monthName = allMonths[calendar.get(Calendar.MONTH)];

        Map<String, Map<String, Integer>> periodRegistry = registry.computeIfAbsent(period, k -> new HashMap<>());

        Map<String, Integer> monthRegistry = periodRegistry.computeIfAbsent(monthName, p -> new HashMap<String, Integer>());
        int count = monthRegistry.getOrDefault(placementType, 0) + 1;
        monthRegistry.put(placementType, count);
    }

    private void getFromRegistry(Map<String,Map<String, Map<String, Integer>>> registry, String period, List<String>monthList, List<String> placementTypes, List<Integer> counts){

        if (period == null) {
            //add all months to monthList
            registry.values().forEach(monthsAsKey ->{
                monthList.addAll(monthsAsKey.keySet());
            });

            //add all placementType to placementTypes
            var allPlacements = registry.values().stream().flatMap( col -> col.keySet().stream()).collect(Collectors.toList());
            placementTypes.addAll(allPlacements);

            //add all counts to counts
            var allCounts = registry.values().stream().flatMap( col -> col.values().stream().flatMap(count -> count.values().stream()))
                    .collect(Collectors.toList());
            counts.addAll(allCounts);


        }

    }
    private Map<String, Object> getFromRegistry(Map<String,Map<String, Map<String, Integer>>> registry, String period){
        Map<String, Object> result = new HashMap<>();
        if (period == null) {
            //add all months to monthList
            result.put("months",  registry.values().stream().flatMap( col -> col.keySet().stream()).collect(Collectors.toList()));

//            result.put("months", months);

            //add all placementType to placementTypes
            result.put("placements",
                    registry.values().stream().flatMap( col -> col.values().stream().map(Map::keySet)).flatMap(Collection::stream).toList()
            );

            //add all counts to counts
            result.put("counts",  registry.values().stream().flatMap( col -> col.values().stream().flatMap(count -> count.values().stream()))
                    .collect(Collectors.toList()));


            return result;

        }


        //add period months
        result.put("months", new ArrayList<>(registry.get(period).keySet()));

        //add period placementType
        result.put("placements",
                registry.get(period).values().stream().flatMap( col -> col.keySet().stream()).toList()
        );

        //add period counts
        result.put("counts",  registry.get(period).values().stream().flatMap( col -> col.values().stream())
                .collect(Collectors.toList()));


        return result;

    }

}
