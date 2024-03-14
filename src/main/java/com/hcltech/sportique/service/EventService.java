package com.hcltech.sportique.service;


import com.hcltech.sportique.dto.EventDto2;
import com.hcltech.sportique.dto.EventDto3;
import com.hcltech.sportique.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    Event createEventForOrganization(Integer organizationId, EventDto2 event,List<String> sportsName );
    List<EventDto3> getAllEventsByOrganization(Integer organizationId);

    EventDto3 getEventById(Integer eventId);


}
