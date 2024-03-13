package com.hcltech.sportique.service;

import com.hcltech.sportique.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.dto.RefreshTokenRequest;
import com.hcltech.sportique.dto.SigninRequest;
import com.hcltech.sportique.dto.UserSignUpRequest;
import com.hcltech.sportique.entity.User;

public interface AuthenticationService {


    JwtAuthenticationResponse signin(SigninRequest signinRequest);
//    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtAuthenticationResponse userRefreshToken(RefreshTokenRequest refreshTokenRequest);

    String userSignUp(UserSignUpRequest userSignUpRequest);
}
