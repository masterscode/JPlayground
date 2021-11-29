package com.play.payloads.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponse {
    private Long id;
    private String fullName;
    private String address;
    private String email;
}
