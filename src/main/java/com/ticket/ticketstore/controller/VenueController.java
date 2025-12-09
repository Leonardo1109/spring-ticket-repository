package com.ticket.ticketstore.controller;

import org.springframework.web.bind.annotation.*;

import com.ticket.ticketstore.dto.VenueDTO;
import com.ticket.ticketstore.service.VenueService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenues(){
        return ResponseEntity.ok(venueService.getAllVenues());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getVenueById(@PathVariable Integer id){
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueDTO dto){
        return ResponseEntity.ok(venueService.createVenue(dto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<VenueDTO> updateVenue(@PathVariable Integer id, @RequestBody VenueDTO dto){
        return ResponseEntity.ok(venueService.saveVenue(dto, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Integer id){
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
