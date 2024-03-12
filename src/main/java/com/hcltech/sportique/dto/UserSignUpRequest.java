package com.hcltech.sportique.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String mobileNumber;
    private String email;
    private String password;
}
