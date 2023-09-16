package com.library.controller;

import com.library.controller.request.LoginRequest;
import com.library.controller.response.AuthResponse;
import com.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
       return ResponseEntity.ok(authService.login(loginRequest));
    }
}
