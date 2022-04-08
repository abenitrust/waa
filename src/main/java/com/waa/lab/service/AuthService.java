package com.waa.lab.service;

import com.waa.lab.domain.dto.request.LoginRequestDto;
import com.waa.lab.domain.dto.request.RegisterRequestDto;
import com.waa.lab.domain.dto.response.LoginResponseDto;
import com.waa.lab.domain.dto.response.MessageResponseDto;
import com.waa.lab.exception.NewUserException;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequest) throws BadCredentialsException;
    MessageResponseDto register(RegisterRequestDto registerDto) throws NewUserException;

}
