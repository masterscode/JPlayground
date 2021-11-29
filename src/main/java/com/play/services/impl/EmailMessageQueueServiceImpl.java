package com.play.services.impl;

import com.play.models.EmailMessage;
import com.play.models.EmailMessageQueue;
import com.play.models.User;
import com.play.repository.EmailMessageQueueRepository;
import com.play.services.interfaces.EmailMessageQueueService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class EmailMessageQueueServiceImpl implements EmailMessageQueueService {
    Logger logger = LoggerFactory.getLogger(EmailMessageQueueServiceImpl.class);
    private final EmailMessageQueueRepository emailMessageQueueRepository;
    @Override
    public void addMessageToQueue(User user, EmailMessage message) {
        logger.info("adding a new message to the queue");
        var queue = EmailMessageQueue.builder()
                        .message(message)
                                .user(user)
                                        .build();
        emailMessageQueueRepository
                .save(queue);

    }

    @Scheduled(fixedDelay = 5000)
    private void sendMessages(){
        logger.info("starting attempts to send messages on the queue");
        var queueList = emailMessageQueueRepository.findAllBySent(false);


        if (queueList.isEmpty()){
            logger.info("No pending mails to attend to!!");
            return;
        }

        queueList.forEach(messageQueueItem->{

            try {
                int attempts = messageQueueItem.getSendAttempts();

                logger.info("Invoked email service");
                logger.info("sending message to => " + messageQueueItem.getUser().getEmail());
                messageQueueItem.setSendAttempts(attempts + 1);

                messageQueueItem.setSent(true);
                emailMessageQueueRepository
                        .save(messageQueueItem);

            }catch (Exception e){
                int attempts = messageQueueItem.getSendAttempts();

                if (attempts >= 3)  logger.error("Attention is needed for this email");
                logger.error("Error sending email", e);
                messageQueueItem.setSendAttempts(attempts + 1);

                emailMessageQueueRepository
                        .save(messageQueueItem);
            }
                }
        );

    }
}
