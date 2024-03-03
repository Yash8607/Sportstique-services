package com.hcltech.sportique.controller;

import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.service.UserService;
import com.hcltech.sportique.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
      @PostMapping("/user/save")
    public ResponseEntity<User> saveUserDetails(@RequestBody User user){
        User user1=userServiceImpl.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user1);

    }
}
