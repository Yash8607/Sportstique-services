package com.hcltech.sportique.dto;

import com.hcltech.sportique.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private Role role;
    private Integer id;
    private String name;
    private String logo;





}
