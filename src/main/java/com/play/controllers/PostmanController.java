package com.play.controllers;


import com.play.payloads.requests.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postman")
public class PostmanController {

    @PostMapping
    public ResponseEntity<String> doTrial(@RequestBody UserRegistrationRequest request){
        long id = request.getId() <= 0 ? 0 : request.getId();

        return ResponseEntity.ok("trial endpoint " + id );
    }
}
