package com.springapp.inventoryapi;

import com.springapp.inventoryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Autowired
    private UserService userService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login").authenticated()
                        .requestMatchers("/category/add").hasRole("EXECUTIVE")  // Check without "ROLE_"
                        .requestMatchers("/category/update/{id}").hasRole("EXECUTIVE")
                        .requestMatchers("/category/delete/{id}").hasRole("EXECUTIVE")
                        .requestMatchers("/category/all").permitAll()
                        .requestMatchers("/customer/add").permitAll()
                        .requestMatchers("/order/all").hasRole("SUPPLIER")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withUsername("harry@gmail.com")
//                .password(passwordEncoder().encode("potter"))
//                .roles("CUSTOMER")
//                .build();
//
//        UserDetails admin = User.withUsername("jack@executive.com")
//                .password(passwordEncoder().encode("reacher"))
//                .roles("EXECUTIVE")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private DaoAuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(userService);
        return dao;
    }
}

