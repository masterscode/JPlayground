package com.play.services.interfaces;

import com.play.models.EmailMessage;

public interface EmailMessageService {
    EmailMessage getEmailMessageByTitle(String title);
}
