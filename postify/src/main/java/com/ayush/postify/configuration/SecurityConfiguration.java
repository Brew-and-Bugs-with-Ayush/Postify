package com.ayush.postify.configuration;

import com.ayush.postify.domain.entity.User;
import com.ayush.postify.jwt.JwtFilter;
import com.ayush.postify.repository.UserRepository;
import com.ayush.postify.service.MyCustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtFilter jwtFilter) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET , "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET ,"/api/v1/tags/**").permitAll()
                        .requestMatchers(HttpMethod.GET , "api/v1/posts/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        MyCustomUserDetailService myCustomUserDetailService = new MyCustomUserDetailService(userRepository);

        String email = "ayush@gmail.com";
        userRepository.findByEmail(email).orElseGet(
                ()-> {
                    User user = User.builder()
                            .name("Test User")
                            .email(email)
                            .password(passwordEncoder.encode("password"))
                            .build();
                    return userRepository.save(user);
                });
        return myCustomUserDetailService;
    }
}
