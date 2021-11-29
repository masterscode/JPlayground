package com.play.controllers;

import com.play.payloads.requests.UserRegistrationRequest;
import com.play.payloads.response.UserRegistrationResponse;
import com.play.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public @ResponseBody UserRegistrationResponse saveUser(@Valid @RequestBody UserRegistrationRequest request){
        return userService.saveUser(request);
    }
}
