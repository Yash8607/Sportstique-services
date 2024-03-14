package com.hcltech.sportique.dto;

import com.hcltech.sportique.entity.Sports;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto2 {
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

    private List<String> sports;
}
