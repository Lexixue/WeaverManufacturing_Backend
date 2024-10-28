package com.csi.weavermanufacturing_backend.tools;

import com.csi.weavermanufacturing_backend.util.JwtUtils;
import com.csi.weavermanufacturing_backend.tools.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtils jwtUtils) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
