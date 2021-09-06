package com.play.jSE;

import java.util.List;

public class Utils {

    public static <T extends Comparable<T>> T max(T first, T second){
        return first.compareTo(second) < 0  ? second : first;
    }

    public static void printUser(User user){
        System.out.println(user );
    }

    public static void printUsers(List<?> users){
        Object obj = users.get(0);
    }
}