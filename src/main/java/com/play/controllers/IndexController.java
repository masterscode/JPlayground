package com.play.controllers;

import com.play.models.UserEntity;
import com.play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    private final UserRepository repository;

    @GetMapping
    public List<UserEntity> indexPath() {
        return repository.findAll().stream().filter(entity -> entity.getId() >= 1).toList();
    }
}
