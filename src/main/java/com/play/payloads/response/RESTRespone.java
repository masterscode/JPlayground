package com.play.payloads.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class RESTRespone<T> {
    private String message;
    private String error;
    private T data;
    private final LocalDateTime timeStamp = LocalDateTime.now();
}
