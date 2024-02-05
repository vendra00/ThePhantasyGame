package com.carbon.thephantasyrpg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template config.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Rest template.
     * @return rest template.
     */
    @Bean
    public RestTemplate restTemplate() {return new RestTemplate();}
}
