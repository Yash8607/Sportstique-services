package com.hcltech.sportique.service;

import com.hcltech.sportique.entity.Sports;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportsService {
    Sports addSport(Sports sports);
    List<Sports> getAllSports();
}
