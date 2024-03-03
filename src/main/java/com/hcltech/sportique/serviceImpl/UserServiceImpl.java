package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.repository.UserRepository;
import com.hcltech.sportique.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);

    }
}
