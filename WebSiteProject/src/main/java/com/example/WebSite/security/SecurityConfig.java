package com.example.WebSite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.WebSite.entity.Role.ROLE_ADMIN;
import static com.example.WebSite.entity.Role.ROLE_USER;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/post/list/**").permitAll()
                                .requestMatchers("/users/list/**").hasAuthority(ROLE_ADMIN.name())
                                .requestMatchers("/post/delete/**").hasAuthority(ROLE_ADMIN.name())
                                .requestMatchers("/post/update/**").hasAuthority(ROLE_ADMIN.name())
                                .requestMatchers("/post/add/**").hasAuthority(ROLE_USER.name())
                                .requestMatchers("/post/**").hasAnyAuthority(ROLE_ADMIN.name(), ROLE_USER.name())
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return security.build();
    }
}


