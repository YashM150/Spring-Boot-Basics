package com.matthe.ecom.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for stateless APIs
                .cors(cors -> {})
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/register").permitAll()  // Allow registration
                        .requestMatchers("/api/auth/register/user").permitAll()  // Allow User registration
                        .requestMatchers("/api/auth/register/admin").permitAll()  // Allow User registration
                        .requestMatchers("/api/auth/login").permitAll()     // Allow login
                        .requestMatchers("/api/auth/logout").permitAll()
                        .requestMatchers("/api/auth/user").permitAll()
                        .requestMatchers("/api/products").permitAll()
                        .requestMatchers("/api/auth/session").permitAll()
                        .requestMatchers("/api/product/*").permitAll()
                        .requestMatchers("/api/product/*/image").permitAll()
                        .anyRequest().authenticated()                          // Secure all other endpoints
                )
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // Create a session if required
                .invalidSessionUrl("/api/auth/login")  // Redirect to login if session is invalid
            );
             

        return http.build();
    }

//    @Bean
//    public CustomAuthenticationFilter customAuthenticationFilter() {
//        return new CustomAuthenticationFilter();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
