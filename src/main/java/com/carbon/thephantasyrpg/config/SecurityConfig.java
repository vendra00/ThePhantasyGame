package com.carbon.thephantasyrpg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The SecurityConfig class is a configuration class that configures the security settings for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Match endpoints with their required authorization
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/register").permitAll()
                        .requestMatchers("/home/**").authenticated() // Use authenticated for general access control
                        // You can add more matchers here
                        .anyRequest().authenticated() // All other requests need to be authenticated
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Set the logout URL
                        .logoutSuccessUrl("/login?logout")  // Redirect to login on logout
                        .invalidateHttpSession(true)  // Invalidate session on logout
                        .clearAuthentication(true)    // Clear authentication attributes
                        .deleteCookies("JSESSIONID")  // Delete cookies
                        .permitAll()
        );

        // Disable CSRF for Vaadin apps, as Vaadin has its own CSRF protection
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
