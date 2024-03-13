package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.dto.RefreshTokenRequest;
import com.hcltech.sportique.dto.SigninRequest;
import com.hcltech.sportique.dto.UserSignUpRequest;
import com.hcltech.sportique.entity.Role;
import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.repository.UserRepository;
import com.hcltech.sportique.service.AuthenticationService;
import com.hcltech.sportique.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;


    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        User user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var userJwt = jwtService.generateToken(user);


        var userRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(userJwt);
        jwtAuthenticationResponse.setRefreshToken(userRefreshToken);


        jwtAuthenticationResponse.setId(user.getUserId());
        jwtAuthenticationResponse.setRole(user.getRole());


        return jwtAuthenticationResponse;
    }


    public String userSignUp(UserSignUpRequest userSignUpRequest) {
        User user = new User();

        user.setEmail(userSignUpRequest.getEmail());
        user.setName(userSignUpRequest.getName());
        user.setMobileNumber(userSignUpRequest.getMobileNumber());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        user.setGender(userSignUpRequest.getGender());

        user.setRole(Role.USER);
         userRepository.save(user);
         return null;


    }

    public JwtAuthenticationResponse userRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            jwtAuthenticationResponse.setId(user.getUserId());
            jwtAuthenticationResponse.setRole(user.getRole());
            return jwtAuthenticationResponse;
        }
        return null;
    }


}

