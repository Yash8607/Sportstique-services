package com.hcltech.sportique.controller;

import com.hcltech.sportique.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.dto.RefreshTokenRequest;
import com.hcltech.sportique.dto.SigninRequest;
import com.hcltech.sportique.dto.UserSignUpRequest;
import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {

        try{
            JwtAuthenticationResponse response=authenticationService.signin(signinRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Credentials");
        }

    }



    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpRequest signUpRequest) {
        authenticationService.userSignUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfully Completed");
    }

    @PostMapping("/user/refresh")
    public ResponseEntity<JwtAuthenticationResponse> userRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.userRefreshToken(refreshTokenRequest));
    }

}
