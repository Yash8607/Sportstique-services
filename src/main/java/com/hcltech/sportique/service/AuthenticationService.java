package com.hcltech.sportique.service;

import com.hcltech.sportique.dto.*;
import com.hcltech.sportique.entity.Organization;

public interface AuthenticationService {

    Organization signUp(OrganizationSignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);




}
