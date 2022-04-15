package com.play.configurations.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;


@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("== This exception occurred in an async method ==");

        log.error("Throwable", throwable);
        log.error("Method", method);
        log.error("Accompanying objects", objects);

        log.info("== End of async method exception ==");
    }
}
