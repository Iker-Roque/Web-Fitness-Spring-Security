package com.fitness.fitness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) //ajustar la CSRF pARA QUE SE integre con la aplicacion 
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            )
            .formLogin(form -> form.disable()) 
            .logout(logout -> logout
                    .logoutUrl("/logout")         
                    .logoutSuccessUrl("/login?logout") 
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                ); 
        return http.build();
    }
}


