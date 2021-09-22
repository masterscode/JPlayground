package com.play.services.impl;

import com.play.models.User;
import com.play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Cacheable(value = "playground-cache", key = "'play'")
    public User getUser(){
        User user = new User();
        user.setFullName("cached name");
        return  user;
    }
}
