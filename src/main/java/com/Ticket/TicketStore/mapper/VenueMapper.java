package com.Ticket.TicketStore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.Ticket.TicketStore.dto.VenueDTO;
import com.Ticket.TicketStore.model.Venue;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    
    @Mapping(target = "eventsIds", ignore = true)
    VenueDTO venueToVenueDTO(Venue venue);

    @Mapping(target = "eventsIds", ignore = true)
    List<VenueDTO> venuesToVenueDTOs(List<Venue> venues);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    Venue venueDTOToVenue(VenueDTO venueDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    List<Venue> venuesDTOsToVenues(List<VenueDTO> venueDTOs);
}
