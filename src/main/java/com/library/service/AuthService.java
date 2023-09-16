package com.library.service;

import com.library.controller.request.LoginRequest;
import com.library.controller.response.AuthResponse;
import com.library.exception.InvalidCredentialsException;
import com.library.jwt.JwtTokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponse login(LoginRequest loginRequest){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );
        Authentication result = authenticationManager.authenticate(authentication);
        if(result.isAuthenticated()) {
            String jwtToken = jwtTokenProvider.generateToken(result);
            return AuthResponse.builder().status(Boolean.TRUE).token(jwtToken).build();
        }
        throw new InvalidCredentialsException("Kullanici adi veya sifre hatali");
    }
}
