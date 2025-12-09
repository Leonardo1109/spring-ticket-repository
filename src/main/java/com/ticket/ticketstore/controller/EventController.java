package com.ticket.ticketstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticket.ticketstore.dto.*;
import com.ticket.ticketstore.service.EventService;


@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventGetDTO>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventGetDTO> getEventById(@PathVariable Integer id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    
    @PostMapping
    public ResponseEntity<EventGetDTO> createEvent(@Valid @RequestBody EventPostDTO dto){
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventGetDTO> updateEvent(@Valid @RequestBody EventPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(eventService.updateEvent(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title")
    public ResponseEntity<List<EventGetDTO>> getEventByTitle(@RequestParam String title) {
        return ResponseEntity.ok(eventService.getEventByTitle(title));
    }
    
    // GET /api/events/date?date=2025-12-02
    @GetMapping("/date")
    public ResponseEntity<List<EventGetDTO>> getEventByDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(eventService.findByDate(date));
    }
    
    @GetMapping("/name")
    public ResponseEntity<List<EventGetDTO>> getEventByVenueName(@RequestParam String name){
        return ResponseEntity.ok(eventService.findByArtistName(name));
    }
    
    
}
