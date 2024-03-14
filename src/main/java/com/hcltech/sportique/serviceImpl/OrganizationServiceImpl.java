package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.repository.OrganizationRepository;
import com.hcltech.sportique.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return organizationRepository.findByOrganizationEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User NOt Found"));
            }
        };
    }

    public List<Organization> getAllOrganization(){
        return organizationRepository.findAll();
    }
}
