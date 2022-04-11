package com.waa.lab.service.impl;

import com.waa.lab.domain.*;
import com.waa.lab.domain.dto.request.LoginRequestDto;
import com.waa.lab.domain.dto.request.RefreshTokenRequestDto;
import com.waa.lab.domain.dto.request.RegisterRequestDto;
import com.waa.lab.domain.dto.response.LoginResponseDto;
import com.waa.lab.domain.dto.response.MessageResponseDto;
import com.waa.lab.domain.dto.response.RefreshTokenResponseDto;
import com.waa.lab.exception.InvalidRoleException;
import com.waa.lab.exception.NewUserException;
import com.waa.lab.exception.RefreshTokenException;
import com.waa.lab.repository.RefreshTokenRepository;
import com.waa.lab.repository.RoleRepository;
import com.waa.lab.repository.UserRepository;
import com.waa.lab.service.AuthService;
import com.waa.lab.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private  AuthenticationManager authenticationManager;
    private  UserDetailsService userDetailsService;
    private  JwtUtil jwtUtil;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtUtil jwtUtil, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest)
     throws BadCredentialsException {
        try {

            UsernamePasswordAuthenticationToken uT = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(uT);
            AppUserDetails userDetails = (AppUserDetails) userDetailsService.loadUserByUsername(
                    loginRequest.getUsername()
            );

            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getUsername());
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(role -> role.getAuthority())
                    .collect(Collectors.toList());

            // persist refreshToken
            RefreshToken storedToken = refreshTokenRepository.findByUserId(userDetails.getId())
                    .orElse(null);

            if(storedToken == null) {
                storedToken = new RefreshToken();
            }
            storedToken.setUser(userRepository.findById(userDetails.getId()).orElse(null));
            storedToken.setToken(refreshToken);
            refreshTokenRepository.save(storedToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new LoginResponseDto(accessToken, refreshToken, roles);

        } catch (BadCredentialsException e){
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public MessageResponseDto register(RegisterRequestDto registerDto)
        throws NewUserException {

        if(userRepository.findByUsername(registerDto.getUsername()).isPresent()){
            throw new NewUserException("Error:  Username not available");
        }

        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new NewUserException("Error: Email is already in use!");
        }

        User newUser = new User();
        modelMapper.map(registerDto, newUser);

        //password encode
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Get and add roles
        List<Role> mappedRoles = new ArrayList<>();
        RoleEnum roleEnum = RoleEnum.ADMIN; // just to access helper method
        for(String role: registerDto.getRoles()) {
            try {
                mappedRoles.add(
                        roleRepository.findByName(
                                roleEnum.getEnum(role)
                        ).orElseThrow(InvalidRoleException::new)
                );
            } catch (InvalidRoleException e) {
                throw new NewUserException(e.getMessage());
            }
        }
        newUser.setRoles(mappedRoles);

        // save User
        userRepository.save(newUser);

        return new MessageResponseDto("User account successfully created");
    }

    @Override
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto requestTokenDto)
            throws RefreshTokenException {
        String userToken = requestTokenDto.getRefreshToken();
        RefreshToken storedToken = refreshTokenRepository.findByToken(userToken)
                .orElse(null);
        if(storedToken == null || !storedToken.getToken().equals(userToken)) {
            throw new RefreshTokenException("Invalid refresh token");
        }

        if(!jwtUtil.validateToken(userToken)) {
            throw new RefreshTokenException("Token has expired and session has ended. Please login again");
        }

        String subject = jwtUtil.getSubject(userToken);
        String newAccessToken = jwtUtil.generateAccessToken(subject);
        String newRefreshToken = jwtUtil.generateRefreshToken(subject);
        storedToken.setToken(newRefreshToken);
        refreshTokenRepository.save(storedToken);
        return new RefreshTokenResponseDto(newAccessToken, newRefreshToken);
    }


}
