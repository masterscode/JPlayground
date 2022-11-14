package com.play.async_future;


import com.play.exceptions.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncFutureService {


    @Async
    public Future<Object> testAsyncResult(){
        log.info("--> security context {}", SecurityContextHolder.getContext());
        if ( 0 == 0) throw new GenericException("An async future exception");
        return new AsyncResult<>("This result is from future async");
    }
}
