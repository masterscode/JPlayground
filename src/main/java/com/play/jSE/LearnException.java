package com.play.jSE;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LearnException {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("file.txt");
        }catch (FileNotFoundException xp){
            final String msg = xp.getLocalizedMessage();
            System.out.println(msg);
        }
    }

    public static <T extends Number, Comparable> T arithmeticSum (T a , T b){
        return a;
    }

    public static <K,V> void print(K key, V value){
        System.out.println(key +" = "+ value);
    }
}
