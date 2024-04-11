package com.carbon.thephantasyrpg.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * The InternationalizationConfig class is a configuration class that configures the internationalization settings for the application.
 */
@Configuration
public class InternationalizationConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("PlayerCreationView", "RaceService", "LoginView");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
