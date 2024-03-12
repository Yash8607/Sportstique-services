package com.hcltech.sportique.service;

import com.hcltech.sportique.entity.Organization;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {



    UserDetailsService userDetailsService();
    List<Organization> getAllOrganization();
}
