package com.hcltech.sportique.controller;

import com.hcltech.sportique.dto.UserDto;
import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping
    public String sayHello(){
        return "Hello";
    }
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Integer userId){
        return userServiceImpl.getuserById(userId);
    }

}
