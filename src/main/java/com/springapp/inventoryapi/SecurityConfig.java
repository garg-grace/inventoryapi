package com.springapp.inventoryapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Explicitly disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/category/add").hasRole("EXECUTIVE") // Admin role for /admin/** paths
                        .requestMatchers("/category/update/{id}").hasRole("EXECUTIVE") // User role for /user/** paths
                        .requestMatchers("/category/delete/{id}").hasRole("EXECUTIVE")
                        .requestMatchers("/category/all").permitAll() // Public endpoints accessible to anyone
                        .anyRequest().authenticated() // Any other request must be authenticated
                )
                .httpBasic(Customizer.withDefaults()); // Enable logout functionality

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("harry@gmail.com")
                .password(passwordEncoder().encode("potter"))
                .roles("CUSTOMER")
                .build();

        UserDetails admin = User.withUsername("jack@executive.com")
                .password(passwordEncoder().encode("reacher"))
                .roles("EXECUTIVE")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

