package com.hcltech.sportique.controller;

import com.hcltech.sportique.dto.*;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.service.AuthenticationService;
import com.hcltech.sportique.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final OrganizationService organizationService;

    @PostMapping("/signup")
    public ResponseEntity<Organization> signUp(@RequestBody OrganizationSignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

//    @PostMapping("/user/signup")
//    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest signUpRequest){
//        return ResponseEntity.ok(authenticationService.userSignUp(signUpRequest));
//    }
//    @PostMapping("/user/refresh")
//    public ResponseEntity<JwtAuthenticationResponse> userRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
//        return ResponseEntity.ok(authenticationService.userRefreshToken(refreshTokenRequest));
//    }

    @GetMapping("/organization/show")
    public List<Organization> getAllOrganization(){
        return organizationService.getAllOrganization();
    }

}
