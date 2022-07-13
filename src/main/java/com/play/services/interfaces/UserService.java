package com.play.services.interfaces;

import com.play.payloads.requests.UserRegistrationRequest;
import com.play.payloads.response.UserRegistrationResponse;
import org.springframework.scheduling.annotation.Async;

public interface UserService {
    UserRegistrationResponse saveUser(UserRegistrationRequest request);

    @Async()
    void logNumbersAsync();
}
