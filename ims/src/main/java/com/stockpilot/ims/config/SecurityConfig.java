package com.stockpilot.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        // 1. Authorization rules
        .authorizeHttpRequests(auth -> auth
            // Allow all users (permitAll) to access the /login page
            .requestMatchers("/login").permitAll()

            // Allow static resources (CSS/Bootstrap) to be loaded without login
            .requestMatchers("/style.css", "/webjars/**").permitAll()

            // All other requests (/**) require a user to be authenticated (logged in)
            .anyRequest().authenticated())

        // 2. Form Login configuration
        .formLogin(form -> form
            // Set the specific URL for the login page
            .loginPage("/login")
            // Set the default success URL after login
            .defaultSuccessUrl("/", true))
        // 3. Logout configuration
        .logout(logout -> logout
            // The URL to handle logout (matches the form action in products.html)
            .logoutUrl("/logout")
            // Redirect user back to the login page after successful logout
            .logoutSuccessUrl("/login?logout"));

    return http.build();
  }
}