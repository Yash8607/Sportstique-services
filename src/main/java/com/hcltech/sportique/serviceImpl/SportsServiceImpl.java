package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.entity.Sports;
import com.hcltech.sportique.repository.SportsRepository;
import com.hcltech.sportique.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportsServiceImpl implements SportsService {

    private final SportsRepository sportsRepository;

    public Sports addSport(Sports sports){
        return sportsRepository.save(sports);
    }

    public List<Sports> getAllSports(){
        List<Sports> allSports = sportsRepository.findAll();
        return allSports;
    }

}
