package com.nicki.enterpriseprojektarbete.configurations;

import com.nicki.enterpriseprojektarbete.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    private final AppPasswordConfig bcrypt;
    private final UserModelService userModelService;

    @Autowired
    public AppSecurityConfig(AppPasswordConfig bcrypt, UserModelService userModelService) {
        this.bcrypt = bcrypt;
        this.userModelService = userModelService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf().disable() //to use postman
                .authorizeHttpRequests( requests -> {
                    requests
                            .requestMatchers("/login", "/logout","/error", "/register", "/rest/**").permitAll()
                            .requestMatchers("/admin", "/createQuiz", "/users", "/user/{id}", "/users/delete").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                })
                .formLogin( formlogin -> {
                            formlogin.loginPage("/login");
                        }
                )
                .rememberMe(rememberMe ->
                        rememberMe
                            .rememberMeParameter("remember-me-token")
                            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(14))
                            .key("someSecureKey")
                            .userDetailsService(userModelService)

                )

                .logout(logout ->
                        logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("remember-me-token", "JSESSIONID")
                )
                .authenticationProvider(authenticationOverride());

        return http.build();
    }


    public DaoAuthenticationProvider authenticationOverride() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userModelService);
        provider.setPasswordEncoder(bcrypt.bCryptPasswordEncoder());

        return provider;
    }
}
