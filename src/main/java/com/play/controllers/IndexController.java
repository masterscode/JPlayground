package com.play.controllers;

import com.play.models.User;
import com.play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    private final UserRepository repository;

    @GetMapping("/{id}")
    public List<User> indexPath(@PathVariable("id") Long id) throws MalformedURLException {
        final URL url = new URL("http", "localhost", 80, null);

        return repository.findAll().stream().filter(entity -> entity.getId() >= 1).toList();
    }
}
