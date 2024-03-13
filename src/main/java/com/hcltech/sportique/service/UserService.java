package com.hcltech.sportique.service;

import com.hcltech.sportique.dto.UserDto;
import com.hcltech.sportique.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

 UserDto getuserById(Integer id);

}
