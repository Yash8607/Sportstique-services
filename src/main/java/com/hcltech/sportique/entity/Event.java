package com.hcltech.sportique.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;
    private String eventName;
    private String description;
    private String location;
    private LocalDate eventDate;
    private String eventDuration;
    private LocalDate eventRegDeadline;
    private String prizeDetails;
    private String adminContactNumber;
    private String associatedOrgnisationId;
    private String eventTime;
    private String adminName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "events_sports", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Sports> sports;




}

