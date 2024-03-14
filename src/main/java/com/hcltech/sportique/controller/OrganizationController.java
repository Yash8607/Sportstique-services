package com.hcltech.sportique.controller;

import com.hcltech.sportique.dto.EventDto;
import com.hcltech.sportique.dto.EventDto2;
import com.hcltech.sportique.entity.Event;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.repository.OrganizationRepository;
import com.hcltech.sportique.service.EventService;
import com.hcltech.sportique.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrganizationController {


    private final EventService eventService;

    @PostMapping("/{organizationId}/events")
    public ResponseEntity<EventDto> createEventForOrganization(@PathVariable Integer organizationId, @RequestBody EventDto2 event) {

        Event e1=eventService.createEventForOrganization(organizationId, event, event.getSports());
        EventDto e2=new EventDto();
        e2.setEventId(e1.getEventId());
        e2.setMessage("Event Created Successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(e2);
    }



}
