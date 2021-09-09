package com.play.services.impl;

import com.play.models.User;
import com.play.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    public User getUser(){
        return  null;
    }
}
