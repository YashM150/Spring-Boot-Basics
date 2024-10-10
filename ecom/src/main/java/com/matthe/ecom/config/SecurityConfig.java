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
                        .requestMatchers("/api/products").permitAll()
//                        .requestMatchers("/api/products/{id}/image").permitAll()
//                        .requestMatchers("/api/products/{id}").permitAll()
                        .anyRequest().authenticated()                          // Secure all other endpoints
                );
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add your custom filter
             

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
