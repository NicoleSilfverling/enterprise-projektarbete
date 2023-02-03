package com.nicki.enterpriseprojektarbete.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests( requests -> {
                    requests
                            .requestMatchers("/login", "/logout","/error", "/register", "/rest/**").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                })


                .formLogin( formlogin -> {
                            formlogin.loginPage("/login");
                        }
                );

        return http.build();
    }
}
