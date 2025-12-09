package com.Ticket.TicketStore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Ticket.TicketStore.dto.*;
import com.Ticket.TicketStore.mapper.EventMapper;
import com.Ticket.TicketStore.model.*;
import com.Ticket.TicketStore.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final VenueRepository venueRepository;
    private final ArtistRepository artistRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public List<EventGetDTO> getAllEvents(){
        return eventMapper.eventsToEventGetDTOs(eventRepository.findAll());
    }

    @Transactional
    public EventGetDTO getEventById(Integer id){
        return eventMapper.eventToEventGetDTO(eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento no encontrado")));
    }

    @Transactional
    public EventGetDTO createEvent(EventPostDTO dto){
        Event event = eventMapper.eventPostDTOToEvent(dto);

        Venue venue = venueRepository.findById(dto.getVenueId())
            .orElseThrow(() -> new RuntimeException("Venue no encontrado"));

        List<Artist> artists = artistRepository.findAllById(dto.getArtistIds());

        event.setArtists(artists);
        event.setVenue(venue);

        event = eventRepository.save(event);

        return eventMapper.eventToEventGetDTO(event);

    }

    @Transactional
    public EventGetDTO updateEvent(EventPostDTO dto, Integer id){
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento no encoentrado"));

        // venue, artist, tickets
        List<Ticket> tickets = ticketRepository.findByEvent(event);
        Venue venue = venueRepository.findById(dto.getVenueId())
            .orElseThrow(() -> new RuntimeException("Venue no encontrado"));
        List<Artist> artists = artistRepository.findAllById(dto.getArtistIds());

        event.setTitle(dto.getTitle());
        event.setDate(dto.getDate());
        event.setVenue(venue);
        event.setTickets(tickets);
        event.setArtists(artists);

        event = eventRepository.save(event);

        return eventMapper.eventToEventGetDTO(event);
    }

    public void deleteEvent(Integer id){
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventRepository.deleteById(id);
    }

    public List<EventGetDTO> getEventByTitle(String title){
        return eventMapper.eventsToEventGetDTOs(
            eventRepository.findByTitleContainingIgnoreCase(title));
    }

    public List<EventGetDTO> findByDate(LocalDate date){
        return eventMapper.eventsToEventGetDTOs(
            eventRepository.findByDate(date)
        );
    }

    public List<EventGetDTO> findByArtistName(String name){
        return eventMapper.eventsToEventGetDTOs(
            eventRepository.findEventsByArtistsName(name)
        );
    }
}
