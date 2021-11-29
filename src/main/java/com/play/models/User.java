package com.play.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    private String fullName;
    private String address;
    private String email;

//    @Embedded
//    private Account account;

}
