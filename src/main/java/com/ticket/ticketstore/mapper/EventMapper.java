package com.ticket.ticketstore.mapper;

import java.util.List;

import org.mapstruct.*;

import com.ticket.ticketstore.dto.*;
import com.ticket.ticketstore.model.Event;

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
