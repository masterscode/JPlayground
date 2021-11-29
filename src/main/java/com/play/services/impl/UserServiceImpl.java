package com.play.services.impl;

import com.play.models.EmailMessage;
import com.play.models.User;
import com.play.payloads.requests.UserRegistrationRequest;
import com.play.payloads.response.UserRegistrationResponse;
import com.play.repository.UserRepository;
import com.play.services.interfaces.EmailMessageQueueService;
import com.play.services.interfaces.EmailMessageService;
import com.play.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailMessageQueueService messageQueueService;
    private final EmailMessageService emailMessageService;

    @Cacheable(value = "playground-cache", key = "'play'")
    public User getUser(){
        User user = new User();
        user.setFullName("cached name");
        return  user;
    }

    @Override
    public UserRegistrationResponse saveUser(UserRegistrationRequest request) {
        ModelMapper  mapper = new ModelMapper();
        User newUser = User.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .fullName(request.getFullName())
                .build();
        User savedUser = userRepository.save(newUser);

        EmailMessage message = emailMessageService.getEmailMessageByTitle("Welcome message");
        messageQueueService.addMessageToQueue(savedUser, message);




        return mapper.map(savedUser, UserRegistrationResponse.class);
    }
}
