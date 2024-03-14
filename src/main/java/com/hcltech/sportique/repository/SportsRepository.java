package com.hcltech.sportique.repository;

import com.hcltech.sportique.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface SportsRepository extends JpaRepository<Sports, Integer> {
    Optional<Sports> findByName(String name);
}
