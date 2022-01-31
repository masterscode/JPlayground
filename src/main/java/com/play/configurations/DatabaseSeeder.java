package com.play.configurations;

import com.play.payloads.requests.UserRegistrationRequest;
import com.play.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final UserService userService;

    public void saveUsers(){
        log.info("saving users to the database");

        List<UserRegistrationRequest> requests = List.of(
//                new UserRegistrationRequest("test full name","my address", "dummy1@email.com"),
//                new UserRegistrationRequest("test full name","my address", "dummy2@email.com"),
//                new UserRegistrationRequest("test full name","my address", "dummy3@email.com"),
//                new UserRegistrationRequest("test full name","my address", "dummy4@email.com"),
//                new UserRegistrationRequest("test full name","my address", "dummy5@email.com"),
                new UserRegistrationRequest("test full name","my address", "dummy6@email.com")
        );

        var result = requests.stream().map(request -> userService.saveUser(request)).toList();

        log.info("finished saving users to the database");
        log.info("Responses ==>  ", result);

    }
}
