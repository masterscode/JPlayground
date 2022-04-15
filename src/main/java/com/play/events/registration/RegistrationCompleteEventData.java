package com.play.events.registration;

import com.play.models.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class RegistrationCompleteEventData extends ApplicationEvent {
    @Getter
    private final  User user;

    public RegistrationCompleteEventData(User source) {
        super(source);
        user = source;
    }
}
