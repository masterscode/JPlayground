package com.play.services.interfaces;

import com.play.models.EmailMessage;
import com.play.models.User;

public interface EmailMessageQueueService {
    void addMessageToQueue(User user, EmailMessage message);
}
