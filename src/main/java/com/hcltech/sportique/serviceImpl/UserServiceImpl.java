package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.dto.UserDto;
import com.hcltech.sportique.entity.Role;
import com.hcltech.sportique.entity.User;
import com.hcltech.sportique.repository.UserRepository;
import com.hcltech.sportique.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User NOt Found"));
            }
        };
    }

    @Override
    public UserDto getuserById(Integer id) {

      UserDto dto=new UserDto();
        User user= userRepository.findById(id).orElseThrow(()->new RuntimeException("User Details are not found"));
        return new UserDto(user.getUserId(), user.getName(), user.getEmail(),user.getRole());
    }


}
