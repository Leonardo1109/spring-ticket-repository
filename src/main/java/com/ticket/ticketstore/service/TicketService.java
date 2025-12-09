package com.ticket.ticketstore.service;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;

import com.ticket.ticketstore.dto.*;
import com.ticket.ticketstore.mapper.TicketMapper;
import com.ticket.ticketstore.model.*;
import com.ticket.ticketstore.model.id.CustomerTicketId;
import com.ticket.ticketstore.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerTicketRepository customerTicketRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public List<TicketGetDTO> getAllTickets(){
        return ticketMapper.ticketsToTicketGetDTOs(ticketRepository.findAll());
    }

    @Transactional
    public TicketGetDTO getTicketById(Integer id){
        return ticketMapper.ticketToTicketGetDTO(ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado")));
    }

    @Transactional
    public TicketGetDTO createTicket(TicketPostDTO dto){
        Ticket ticket = ticketMapper.ticketPostDTOToTicket(dto);

        //clientes y evento
        Event event = eventRepository.findById(dto.getEventId())
            .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        ticket.setEvent(event);
        ticket = ticketRepository.save(ticket);

        // clientes, tengo los ids de los clientes, necesito generar la relacion y darla al objeto
        // Relacion clientes tickets
        List<CustomerTicket> customersTickets = new ArrayList<>();

        for (Integer i : dto.getCustomerIds()) {
            CustomerTicket ct = new CustomerTicket();

            CustomerTicketId id = new CustomerTicketId();
            id.setCustomerId(i);
            id.setTicketId(ticket.getId());

            ct.setId(id);
            ct.setDate(LocalDateTime.now());

            customersTickets.add(ct);
        }
        customerTicketRepository.saveAll(customersTickets);
        ticket.setCustomerTickets(customersTickets);

        return ticketMapper.ticketToTicketGetDTO(ticket);

    }

    @Transactional
    public TicketGetDTO updateTicket(TicketPostDTO dto, Integer id){
        
        // Obtener ticket 
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        //Obtener tablas intermedias actuales
        List<CustomerTicket> currentRelations = customerTicketRepository.findByTicket(ticket);

        // IDs actuales de la BD
        Set<Integer> currentIds = new HashSet<>();
        for (CustomerTicket relation : currentRelations) {
            currentIds.add(relation.getId().getCustomerId());
        }

        // id del cliente del dto [2,3]
        Set<Integer> newIds = new HashSet<>(dto.getCustomerIds());

        // ids del cliente a borrar  -> current [1,2]
        Set<Integer> idsToRemove = new HashSet<>(currentIds);
        idsToRemove.removeAll(newIds); // [1]

        // ids a borrar
        Set<Integer> idsToAdd = new HashSet<>(newIds);
        idsToAdd.removeAll(currentIds); // [2,3] - [1,2] ->  [3]

        // borrar idsToRemove
        for (CustomerTicket ct : currentRelations) {
            if (idsToRemove.contains(ct.getId().getCustomerId())) {
                customerTicketRepository.delete(ct);
            }
        }

        // crear idsToAdd
        for (Integer customerId : idsToAdd) {

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    
            CustomerTicketId ctId = new CustomerTicketId();
            ctId.setCustomerId(customerId);
            ctId.setTicketId(ticket.getId());
    
            CustomerTicket newRelation = CustomerTicket.builder()
                    .id(ctId)
                    .ticket(ticket)
                    .customer(customer)
                    .date(LocalDateTime.now())
                    .build();
    
            customerTicketRepository.save(newRelation);
        }

        Event event = eventRepository.findById(dto.getEventId())
            .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        ticket.setPrice(dto.getPrice());
        ticket.setSeat(dto.getSeat());
        ticket.setEvent(event);

        Ticket updated = ticketRepository.save(ticket);

        return ticketMapper.ticketToTicketGetDTO(updated);
    

    }

    public void deteleTicket(Integer id){
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket no encontrado");
        }
        ticketRepository.deleteById(id);
    }
}
