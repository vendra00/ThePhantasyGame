package com.carbon.thephantasyrpg.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The SecurityConfig class is a configuration class that configures the security settings for the application.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // First, configure specific matchers
        http.authorizeHttpRequests((auth) -> {
            auth
                    .requestMatchers(HttpMethod.GET, "/images/*.png").permitAll()
                    .requestMatchers(HttpMethod.GET, "/home").authenticated();
            // Do not add anyRequest() here
        });

        // Now call Vaadin's configuration
        super.configure(http);

        // Configure form login and logout after calling super
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
