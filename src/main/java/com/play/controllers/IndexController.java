package com.play.controllers;

import com.play.controllers.payloads.Req;
import com.play.models.User;
import com.play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
private UserRepository userRepository;
    @PostMapping("/")
    public User indexPath(@Valid @RequestBody Req req)  {
        return userRepository.findByEmail("unknown");
    }


}
