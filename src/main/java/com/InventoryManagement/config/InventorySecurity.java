package com.InventoryManagement.config;

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
public class InventorySecurity {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService testUser(PasswordEncoder passwordEncoder){
       User.UserBuilder user= User.builder();
        UserDetails user1=user.username("smith").password(passwordEncoder().encode("smith123"))
                .roles("Admin").build();
        UserDetails user2=user.username("ani").password(passwordEncoder.encode("ani123"))
                .roles("User").build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(request->request.requestMatchers("/customer/add-customer","/customer/login").hasAnyRole("Admin").anyRequest().authenticated())
                .csrf(csrf->csrf.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
