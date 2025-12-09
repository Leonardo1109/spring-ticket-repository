package com.Ticket.TicketStore.controller;

import com.Ticket.TicketStore.dto.*;
import com.Ticket.TicketStore.service.TicketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketGetDTO>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketGetDTO> getTicketById(@PathVariable Integer id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<TicketGetDTO> createTicket(@Valid @RequestBody TicketPostDTO dto){
        return ResponseEntity.ok(ticketService.createTicket(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketGetDTO> updateTicket(@Valid @RequestBody TicketPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(ticketService.updateTicket(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer id){
        ticketService.deteleTicket(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
