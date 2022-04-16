package com.play.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

@Configuration
public class FreeMarkerConfig {


    @Primary
    @Bean
    public FreeMarkerConfigurationFactory freeMarkerConfigurationFactory(){
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();

        factory.setTemplateLoaderPath("classpath:email-templates");

        return factory;
    }
}
