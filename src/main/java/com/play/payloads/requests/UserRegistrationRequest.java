package com.play.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegistrationRequest implements Serializable {
    private Long id;
    private String fullName;
    private String address;
    private String email;
}
