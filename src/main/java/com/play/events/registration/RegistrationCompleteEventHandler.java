package com.play.events.registration;

import com.play.services.interfaces.EmailMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
//@Component
public class RegistrationCompleteEventHandler implements ApplicationListener<RegistrationCompleteEventData> {

    private final EmailMessageService messageService;

    @Override
    @Async
    public void onApplicationEvent(RegistrationCompleteEventData applicationCompleteEventData) {
        //...
        System.out.println("========dont call me=========");
        System.out.println("sending message to recipient " + applicationCompleteEventData.getUser().getFullName());
        System.out.println("=================");
    }
}
