package com.play.services.impl;

import com.play.events.registration.RegistrationCompleteEventData;
import com.play.models.EmailMessage;
import com.play.models.User;
import com.play.payloads.requests.UserRegistrationRequest;
import com.play.payloads.response.UserRegistrationResponse;
import com.play.repository.UserRepository;
import com.play.services.interfaces.EmailMessageQueueService;
import com.play.services.interfaces.EmailMessageService;
import com.play.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailMessageQueueService messageQueueService;
    private final EmailMessageService emailMessageService;


    private final ApplicationEventPublisher eventPublisher;


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
        log.info("== built the user object");


        log.info("== raised the send user email event");
        eventPublisher.publishEvent(new RegistrationCompleteEventData(newUser));


        log.info("== call to db to save user");
        User savedUser = userRepository.save(newUser);
        log.info("== call to db to save user complete");

        EmailMessage message = emailMessageService.getEmailMessageByTitle("Welcome message");
//        messageQueueService.addMessageToQueue(savedUser, message);


        log.info("== end of method, returning to caller");
        return mapper.map(savedUser, UserRegistrationResponse.class);
    }


    @EventListener
    @Async
    public void sendMessages(RegistrationCompleteEventData eventData){
        System.out.println("=== sending message to recipient " + eventData.getUser().getFullName());
    }
}
