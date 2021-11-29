package com.play.repository;

import com.play.models.EmailMessageQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailMessageQueueRepository  extends JpaRepository<EmailMessageQueue, Long> {
    List<EmailMessageQueue> findAllBySent(Boolean bool);
}
