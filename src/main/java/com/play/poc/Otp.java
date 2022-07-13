package com.play.poc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Otp {
    public static void main(String[] args) {
        System.out.println(generateOTP1(6));
        time();
    }
    public static String generateOTP(int len) {
        String numbers = "0123456789";

        // Using random method
        SecureRandom random = new SecureRandom();
        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = (char) random.nextInt(len);
//            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }

        return new String(otp);
    }

    public static void time(){
        var l = LocalTime.now().plusMinutes(3L);

        System.out.println(
                LocalTime.now().plusMinutes(3L).format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        );
    }
    public static String generateOTP1(final int lengthOfOTP) {
        StringBuilder generatedOTP = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

            for (int i = 0; i < lengthOfOTP; i++) generatedOTP.append(secureRandom.nextInt(lengthOfOTP));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedOTP.toString();
    }
}
