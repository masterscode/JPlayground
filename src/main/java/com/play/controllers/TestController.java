package com.play.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.play.services.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
public class TestController {
    private final UserService userService;

    @GetMapping("/async")
    public String getAsync(){
        CompletableFuture.runAsync(()->{
            IntStream.range(1, 10000000)
            .forEach(num ->log.info(String.valueOf(num)));
        });
        return "async code started";
    }


}
