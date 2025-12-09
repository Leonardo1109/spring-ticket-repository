package com.Ticket.TicketStore.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Ticket.TicketStore.dto.VenueDTO;
import com.Ticket.TicketStore.mapper.VenueMapper;
import com.Ticket.TicketStore.model.Event;
import com.Ticket.TicketStore.model.Venue;
import com.Ticket.TicketStore.repository.EventRepository;
import com.Ticket.TicketStore.repository.VenueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueMapper venueMapper;
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;

    @Transactional
    public List<VenueDTO> getAllVenues(){
        return venueMapper.venuesToVenueDTOs(venueRepository.findAll());
    }

    @Transactional
    public VenueDTO getVenueById(Integer id){
        return venueMapper.venueToVenueDTO(venueRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venue no encontrado")));
    }

    @Transactional
    public VenueDTO createVenue(VenueDTO dto){
        Venue venue = venueMapper.venueDTOToVenue(dto);

        venue = venueRepository.save(venue);

        return venueMapper.venueToVenueDTO(venue);
    }

    @Transactional
    public VenueDTO saveVenue(VenueDTO dto, Integer id){
        Venue venue = venueRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venue no encontrado"));


        List<Integer> ids = dto.getEventsIds();

        List<Event> events = (ids == null || ids.isEmpty())
                ? Collections.emptyList()
                : eventRepository.findAllById(ids);
        
        venue.setEvents(events);

        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setCapacity(dto.getCapacity());
        venue.setEvents(events);

        venue = venueRepository.save(venue);

        return venueMapper.venueToVenueDTO(venue);
    }

    @Transactional
    public void deleteVenue(Integer id){
        if (!venueRepository.existsById(id)) {
            throw new RuntimeException("Venue no encontrado");
        }
        venueRepository.deleteById(id);
    }
}
