package com.play.models;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@ToString
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    private String name;
    private String number;
    private String bank;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
