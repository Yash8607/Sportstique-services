package com.hcltech.sportique.controller;

import com.hcltech.sportique.dto.*;
import com.hcltech.sportique.entity.Event;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.entity.Sports;
import com.hcltech.sportique.service.AuthenticationService;
import com.hcltech.sportique.service.EventService;
import com.hcltech.sportique.service.OrganizationService;
import com.hcltech.sportique.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final EventService eventService;

    private final SportsService sportsService;

    @PostMapping("/organization/signup")
    public ResponseEntity<?> signUp(@RequestBody OrganizationSignUpRequest signUpRequest){
        try{
        Organization createdOrg = authenticationService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful.");
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Please try again.");
        }

    }

    @PostMapping("/organization/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest){
        try{
            JwtAuthenticationResponse response = authenticationService.signin(signinRequest);
            return ResponseEntity.ok(response);
        }catch(Exception ex){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong credentials");


        }

    }

    @PostMapping("/organization/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }



    @GetMapping("/organization/show")
    public List<Organization> getAllOrganization(){
        return organizationService.getAllOrganization();
    }

    @GetMapping("/{organizationId}/events")
    public ResponseEntity<List<EventDto3>> getAllEventsByOrganization(@PathVariable Integer organizationId){

            List<EventDto3> events= eventService.getAllEventsByOrganization(organizationId);
            return ResponseEntity.ok(events);
    }


    @GetMapping("/{eventId}/eventById")
    public ResponseEntity<?> getEventById(@PathVariable Integer eventId){
        try{
            EventDto3 eventDto3 = eventService.getEventById(eventId);
            return ResponseEntity.ok(eventDto3);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/sports")
        public ResponseEntity<Sports> addSports(@RequestBody Sports sports)
    {
        return ResponseEntity.ok(sportsService.addSport(sports));
    }

    @GetMapping("/allSports")
    public ResponseEntity<List<Sports>> getAllSports(){
        List<Sports> allSports = sportsService.getAllSports();
        return ResponseEntity.ok(allSports);
    }






}
