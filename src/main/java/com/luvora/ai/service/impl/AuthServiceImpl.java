package com.luvora.ai.service.impl;

import com.luvora.ai.dto.auth.AuthResponse;
import com.luvora.ai.dto.auth.LoginRequest;
import com.luvora.ai.dto.auth.SignupRequest;
import com.luvora.ai.entity.User;
import com.luvora.ai.exception.UserAlreadyExistsException;
import com.luvora.ai.mapper.UserMapper;
import com.luvora.ai.repository.UserRepository;
import com.luvora.ai.security.jwt.JwtUtils;
import com.luvora.ai.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    UserMapper userMapper;

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        userRepository.findByUsername(signupRequest.username()).ifPresent(user -> {
            throw new UserAlreadyExistsException("User already exists with username " + signupRequest.username());
        });
        User user = userMapper.toUserEntity(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        userRepository.save(user);

        String token = jwtUtils.generateToken(user);

        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication;
        try{
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        } catch (BadCredentialsException exception){
            log.warn("Authentication failed for user {}", loginRequest.username());
            throw new BadCredentialsException("Invalid email and password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtUtils.generateToken(user);

        return new AuthResponse(accessToken, userMapper.toUserProfileResponse(user));
    }
}
