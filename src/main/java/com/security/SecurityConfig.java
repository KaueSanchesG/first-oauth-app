package com.security;

import com.security.user.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/livre").permitAll()
                        .requestMatchers("/h2-console/*").permitAll()
                        .anyRequest().authenticated()
                );
        http.httpBasic(Customizer.withDefaults());

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

//    @Bean
//    UserDetailsService user(){
//        UserModel model = new UserModel();
//        UserDetails user = User.builder()
//                .username(model.getLogin())
//                .password(passwordEncoder().encode(model.getPassword()))
//                .roles(model.getRole())
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
