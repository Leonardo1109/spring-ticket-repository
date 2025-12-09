package com.ticket.ticketstore.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventGetDTO {
    private Integer id;

    private String title;

    private LocalDate date;

    private VenueDTO venue;

    private List<ArtistDTO> artists;

}
