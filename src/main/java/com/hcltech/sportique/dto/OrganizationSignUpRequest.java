package com.hcltech.sportique.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationSignUpRequest {

    private String organization_name;
    private String organization_location;

    private String organizationEmail;
    private String organization_link;



    private String password;

    private String organization_description;

    private String organization_phoneNumber;

    private String logo;
    private String organization_uniqueId;



}
