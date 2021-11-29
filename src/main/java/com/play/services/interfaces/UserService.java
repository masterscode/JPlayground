package com.play.services.interfaces;

import com.play.payloads.requests.UserRegistrationRequest;
import com.play.payloads.response.UserRegistrationResponse;

public interface UserService {
    UserRegistrationResponse saveUser(UserRegistrationRequest request);
}
