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

    public void saveUsers() {
        log.info("saving users to the database");

        List<UserRegistrationRequest> requests = List.of(
//                new UserRegistrationRequest(2L, "test name 1", "my address 1", "dummy1@email.com"),
//                new UserRegistrationRequest(4L, "test full name 2", "my address 2", "dummy2@email.com"),
//                new UserRegistrationRequest(5L, "test full name 3", "my address 3", "dummy3@email.com"),
//                new UserRegistrationRequest(6L, "test full name 4", "my address 4", "dummy4@email.com"),
//                new UserRegistrationRequest(7L, "test full name 5", "my address 5", "dummy5@email.com"),
                new UserRegistrationRequest(1L, "test full name 6", "my address 6", "dummy6@email.com")
        );

        var result = requests.stream().map(userService::saveUser).toList();

        log.info("finished saving users to the database");

    }
}
