package com.hcltech.sportique.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {
    private String name;
    private String mobileNumber;
    private String email;
    private String gender;
    private String password;
}
