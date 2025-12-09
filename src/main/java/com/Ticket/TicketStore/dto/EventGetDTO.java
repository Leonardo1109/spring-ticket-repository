package com.Ticket.TicketStore.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
