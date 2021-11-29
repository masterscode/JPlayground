package com.play.payloads.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateEmailMessageRequest implements Serializable {
    private String title;
    private String subject;
    private String body;
}
