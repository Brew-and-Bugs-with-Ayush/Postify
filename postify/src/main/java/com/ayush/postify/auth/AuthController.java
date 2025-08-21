package com.ayush.postify.auth;

import com.ayush.postify.domain.dtos.AuthResponseDto;
import com.ayush.postify.domain.dtos.LoginRequestDto;
import com.ayush.postify.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody LoginRequestDto loginRequestDto){
        try {
            UserDetails userDetails = authService.registerUser(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
            );

            String tokenValue = jwtService.generateToken(userDetails);
            AuthResponseDto authResponseDto = AuthResponseDto.builder()
                    .token(tokenValue)
                    .expiresIn(86400)
                    .build();

            return new ResponseEntity<>(authResponseDto , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
