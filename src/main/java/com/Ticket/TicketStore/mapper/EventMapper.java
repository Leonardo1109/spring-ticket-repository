package com.Ticket.TicketStore.mapper;

import java.util.List;

import org.mapstruct.*;

import com.Ticket.TicketStore.dto.*;
import com.Ticket.TicketStore.model.Event;

@Mapper(componentModel = "spring", uses = { VenueMapper.class })
public interface EventMapper {

    EventGetDTO eventToEventGetDTO(Event event);

    List<EventGetDTO> eventsToEventGetDTOs(List<Event> events);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Event eventPostDTOToEvent(EventPostDTO eventPostDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "artists", ignore = true)
    List<Event> eventPostDTOsToEvents(List<EventPostDTO> eventPostDTOs);
}
