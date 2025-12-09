package com.ticket.ticketstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ticket.ticketstore.dto.TicketGetDTO;
import com.ticket.ticketstore.dto.TicketPostDTO;
import com.ticket.ticketstore.model.Ticket;

@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface TicketMapper {

    @Mapping(target = "customers", ignore = true)
    TicketGetDTO ticketToTicketGetDTO(Ticket ticket);

    @Mapping(target = "customers", ignore = true)
    List<TicketGetDTO> ticketsToTicketGetDTOs(List<Ticket> tickets);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerTickets", ignore = true)
    @Mapping(target = "event", ignore = true)
    Ticket ticketPostDTOToTicket (TicketPostDTO ticketPostDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerTickets", ignore = true)
    @Mapping(target = "event", ignore = true)
    List<Ticket> ticketPostDTOsToTickets(List<TicketPostDTO> ticketPostDTOs);

}
