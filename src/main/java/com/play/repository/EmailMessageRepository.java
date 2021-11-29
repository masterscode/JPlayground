package com.play.repository;

import com.play.models.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
    Optional<EmailMessage> findEmailMessageByTitle(String title);
}
