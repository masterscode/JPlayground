package com.play.jSE;

import java.util.List;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public static Boolean verifyPhoneNumber(String phone){
        PhoneNumber number = new PhoneNumber();
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            number = phoneNumberUtil.parse(phone, "NG");
        } catch (NumberParseException e) {
            log.error("Phone number parsing exception ", e);
        }
        System.out.println(phoneNumberUtil.getNumberType(number));
        return phoneNumberUtil.isValidNumber(number);
    }

    public static void main(String[] args) {
        
        log.info(verifyPhoneNumber("+2347035140205").toString());
    }
}