package com.play.models;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    String address;
    private String email;

    Type userType;

    private Account account;

    private enum Type{
        ADMIN, MEMBER
    }

    private enum Gender{
        FEMALE, MALE
    }
}
