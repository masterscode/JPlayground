package com.play.jSE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RotatePlay {
    public static void main(String[] args) {
        String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };

        List<String> mons = Arrays.asList(months);

        System.out.println("Before : " + Arrays.toString(months));

        // September is at 9the position so rotate it 4 times, i,e 13th position, 13%12 = 1
        Collections.rotate(mons, 11);


        System.out.println("After : " + mons);
        System.out.println("sublist : " + mons.subList(2, mons.size()));
    }
}
