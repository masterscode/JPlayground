package com.play.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessageQueue extends BaseEntity implements Serializable {
    @OneToOne(targetEntity = EmailMessage.class)
    private EmailMessage message;

    @OneToOne(targetEntity = User.class)
    private User user;

    private int sendAttempts = 0;

    private boolean sent = false;
}
