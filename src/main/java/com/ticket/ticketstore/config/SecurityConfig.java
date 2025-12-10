package com.ticket.ticketstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/artist/**").hasRole("ADMIN")
                //.requestMatchers("POST", "/api/events/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )

            .httpBasic(basic -> { })

            .formLogin(form -> form.disable());

        return http.build();

    }
}
