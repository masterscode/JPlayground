package com.play.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class BaseConfiguration {
    private final DatabaseSeeder databaseSeeder;

    @EventListener
    public void seedDataBase(ContextRefreshedEvent event) {
        databaseSeeder.saveUsers();
    }

}
