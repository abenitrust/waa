package com.waa.lab.controller;

import com.waa.lab.domain.dto.request.LoginRequestDto;
import com.waa.lab.domain.dto.request.RefreshTokenRequestDto;
import com.waa.lab.domain.dto.request.RegisterRequestDto;
import com.waa.lab.domain.dto.response.MessageResponseDto;
import com.waa.lab.exception.InvalidUserCredentialException;
import com.waa.lab.exception.NewUserException;
import com.waa.lab.exception.RefreshTokenException;
import com.waa.lab.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
           return ResponseEntity.ok().body(authService.login(loginRequest));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerDto) {
        try {
            return ResponseEntity.ok(authService.register(registerDto));
        } catch (NewUserException e){
            return ResponseEntity.badRequest().body(new MessageResponseDto(e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        try {
            return ResponseEntity.ok(authService.refreshToken(refreshTokenRequestDto));
        } catch (RefreshTokenException e) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(e.getMessage()));
        }
    }

}
