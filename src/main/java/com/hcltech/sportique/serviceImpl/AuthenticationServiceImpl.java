package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.dto.*;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.entity.Role;
import com.hcltech.sportique.repository.OrganizationRepository;
import com.hcltech.sportique.service.AuthenticationService;
import com.hcltech.sportique.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final OrganizationRepository organizationRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public Organization signUp(OrganizationSignUpRequest signUpRequest){
        Organization organization=new Organization();

        organization.setOrganizationEmail(signUpRequest.getOrganizationEmail());
        organization.setOrganization_name(signUpRequest.getOrganization_name());
        organization.setOrganization_description(signUpRequest.getOrganization_description());
        organization.setOrganization_location(signUpRequest.getOrganization_location());
        organization.setOrganization_link(signUpRequest.getOrganization_link());
        organization.setOrganization_phoneNumber(signUpRequest.getOrganization_phoneNumber());
        organization.setLogo(signUpRequest.getLogo());


        organization.setRole(Role.ORGANIZATION);


        organization.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Random random=new Random();
        int randomNumber = 10000  + random.nextInt(90000);

        //String randomId = UUID.randomUUID().toString();
        String formatId = "ORG"+ String.valueOf(randomNumber);
        signUpRequest.setOrganization_uniqueId(formatId);
        organization.setOrganization_uniqueId(signUpRequest.getOrganization_uniqueId());


        return organizationRepository.save(organization);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var organization = organizationRepository.findByOrganizationEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(organization);


        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), organization);


        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);




            jwtAuthenticationResponse.setId(organization.getId());
            jwtAuthenticationResponse.setRole(organization.getRole());
            jwtAuthenticationResponse.setName(organization.getOrganization_name());
            jwtAuthenticationResponse.setLogo(organization.getLogo());





        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Organization organization=organizationRepository.findByOrganizationEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), organization)){
            var jwt=jwtService.generateToken(organization);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            jwtAuthenticationResponse.setId(organization.getId());
            jwtAuthenticationResponse.setRole(organization.getRole());
            return jwtAuthenticationResponse;
        }
        return null;
    }





//    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
//        // Authenticate user
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid email or password");
//        }
//
//        var organization = organizationRepository.findByOrganizationEmail(signinRequest.getEmail()).orElse(null);
//        var user = userRepository.findByEmail(signinRequest.getEmail()).orElse(null);
//
//        if (organization != null && "ORGANIZATION".equals(organization.getRole())) {
//            var jwt = jwtService.generateToken(organization);
//            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), organization);
//            return createJwtAuthenticationResponse(jwt, refreshToken, organization.getId(), organization.getRole());
//        } else if (user != null && "USER".equals(user.getRole())) {
//            var jwt = jwtService.generateToken(user);
//            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
//            return createJwtAuthenticationResponse(jwt, refreshToken, user.getUserId(), user.getRole());
//        } else {
//            throw new IllegalArgumentException("Invalid email or password");
//        }
//    }
//
//    private JwtAuthenticationResponse createJwtAuthenticationResponse(String token, String refreshToken, Integer id, Role role) {
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//        jwtAuthenticationResponse.setToken(token);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
//        jwtAuthenticationResponse.setId(id);
//        jwtAuthenticationResponse.setRole(role);
//        return jwtAuthenticationResponse;
//    }

}

