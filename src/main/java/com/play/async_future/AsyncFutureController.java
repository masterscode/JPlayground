package com.play.async_future;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncFutureController {
    private final AsyncFutureService asyncFutureService;
    @GetMapping("/async-future")
    public Object test() throws ExecutionException, InterruptedException {
        log.info("--> security context-controller {}", SecurityContextHolder.getContext());

        return asyncFutureService.testAsyncResult().get();
    }

}
