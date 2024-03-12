package com.hcltech.sportique.service;

import com.hcltech.sportique.dto.*;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.entity.User;

public interface AuthenticationService {

    Organization signUp(OrganizationSignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtAuthenticationResponse userRefreshToken(RefreshTokenRequest refreshTokenRequest);

    User userSignUp(UserSignUpRequest userSignUpRequest);
}
