package com.hcltech.sportique.service;

import com.hcltech.sportique.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(User user);
}
