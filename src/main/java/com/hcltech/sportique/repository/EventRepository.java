package com.hcltech.sportique.repository;

import com.hcltech.sportique.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByOrganizationId(Integer organizationId);


    Optional<Event> findByEventId(Integer eventId);
}
