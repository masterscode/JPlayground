package com.play.models;

import lombok.*;
import org.hibernate.annotations.CascadeType;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseEntity{
    private String fullName;
    private String address;
    private String email;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Account> accounts;
}
