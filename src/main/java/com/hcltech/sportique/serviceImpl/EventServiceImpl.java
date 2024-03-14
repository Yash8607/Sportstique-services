package com.hcltech.sportique.serviceImpl;

import com.hcltech.sportique.dto.EventDto;
import com.hcltech.sportique.dto.EventDto2;
import com.hcltech.sportique.dto.EventDto3;
import com.hcltech.sportique.entity.Event;
import com.hcltech.sportique.entity.Organization;
import com.hcltech.sportique.entity.Sports;
import com.hcltech.sportique.exceptionHandling.EventNotFoundException;
import com.hcltech.sportique.repository.EventRepository;
import com.hcltech.sportique.repository.OrganizationRepository;
import com.hcltech.sportique.repository.SportsRepository;
import com.hcltech.sportique.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SportsRepository sportsRepository;

    public Event createEventForOrganization(Integer organizationId, EventDto2 event,List<String> sportsName ) {
//        return organizationRepository.findById(organizationId)
//                .map(organization -> {
//                    event.setOrganization(organization);
//                    event.setAssociatedOrgnisationId(organization.getOrganization_uniqueId());
//                    return eventRepository.save(event);
//                }).orElseThrow(() -> new RuntimeException("Organization not found"));


        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> new RuntimeException("Organization Not found with Id"));

        List<Sports> sports=sportsName.stream()
                .map(s1 -> sportsRepository.findByName(s1)
                        .orElseThrow(()-> new RuntimeException("Sports not found with name")))
                .collect(Collectors.toList());

      Event ev1 = new Event();
      ev1.setOrganization(organization);
        ev1.setEventId(event.getEventId());
        ev1.setEventDate(event.getEventDate());
        ev1.setEventName(event.getEventName());
        ev1.setEventDuration(event.getEventDuration());
        ev1.setAdminContactNumber(event.getAdminContactNumber());
        ev1.setAssociatedOrgnisationId(organization.getOrganization_uniqueId());
        ev1.setEventTime(event.getEventTime());
        ev1.setDescription(event.getDescription());
        ev1.setEventRegDeadline(event.getEventRegDeadline());
        ev1.setLocation(event.getLocation());
        ev1.setPrizeDetails(event.getPrizeDetails());
        ev1.setAdminName(event.getAdminName());
        ev1.setSports(sports);


return eventRepository.save(ev1);


    }


    public List<EventDto3> getAllEventsByOrganization(Integer organizationId) {
       return eventRepository.findByOrganizationId(organizationId)
               .stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    public EventDto3 getEventById(Integer eventId){
        Event event = eventRepository.findByEventId(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        return convertToDto(event);

    }

private EventDto3 convertToDto(Event event){
    EventDto3 eventDto2=new EventDto3();
    eventDto2.setEventId(event.getEventId());
    eventDto2.setEventDate(event.getEventDate());
    eventDto2.setEventName(event.getEventName());
    eventDto2.setEventDuration(event.getEventDuration());
    eventDto2.setAdminContactNumber(event.getAdminContactNumber());
    eventDto2.setAssociatedOrgnisationId(event.getAssociatedOrgnisationId());
    eventDto2.setEventTime(event.getEventTime());
    eventDto2.setDescription(event.getDescription());
    eventDto2.setEventRegDeadline(event.getEventRegDeadline());
    eventDto2.setLocation(event.getLocation());
    eventDto2.setPrizeDetails(event.getPrizeDetails());
    eventDto2.setAdminName(event.getAdminName());
    eventDto2.setSports(event.getSports());
    return eventDto2;
}



}
