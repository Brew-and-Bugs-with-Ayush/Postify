package com.ayush.postify.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    public UserDetails registerUser(String email , String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email , password));
        return userDetailsService.loadUserByUsername(email);
    }
}
