package com.play.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessage extends BaseEntity {
//    @Column(unique = true)
    private String title;

    private String subject;
    private String body;
}
