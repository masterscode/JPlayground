package com.play.controllers;

import com.play.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final UserService userService;

    @GetMapping("/async")
    public String getAsync(){
        userService.logNumbersAsync();
        return "async code started";
    }


}
