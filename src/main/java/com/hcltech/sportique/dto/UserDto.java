package com.hcltech.sportique.dto;

import com.hcltech.sportique.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String name;
    private String mobileNumber;
    private Role role;
}
