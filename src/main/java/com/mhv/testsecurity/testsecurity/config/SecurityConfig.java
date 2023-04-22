package com.mhv.testsecurity.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//use the below if method level authorisation required
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Manual way of defining users
    /*
    @Bean
    public UserDetailsService userDetailsService(){

        //manual way
        UserDetails normalUser = User
                .withUsername("mhv")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User
                .withUsername("mhv_adm")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        //from database
        UserDetailsService dbUsers = new CustomUserDetailsService();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }
     */

    //fetching user details from DB
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    //Handling all url authentications from here
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers("/public/*").permitAll()
                .requestMatchers("/user/*").hasRole("USER")
                .requestMatchers("/admin/*").hasRole("ADMIN")
                .and().formLogin()
                .and().build();
    }

    //if using method level role check before accessing url, then use below
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable().authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().build();
    }
     */
}
