package com.play.services.impl;

import com.play.models.EmailMessage;
import com.play.repository.EmailMessageRepository;
import com.play.services.interfaces.EmailMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailMessageImpl implements EmailMessageService {
    private final EmailMessageRepository emailMessageRepository;

    @Override
    public EmailMessage getEmailMessageByTitle(String title) {
        return emailMessageRepository.findEmailMessageByTitle(title)
                .orElseGet(()-> emailMessageRepository.save(new EmailMessage("Welcome message", "Welcome to test mails", "the test mail body")));
    }
}
