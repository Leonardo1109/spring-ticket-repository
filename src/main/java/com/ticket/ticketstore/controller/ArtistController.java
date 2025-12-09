package com.ticket.ticketstore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticket.ticketstore.dto.ArtistDTO;
import com.ticket.ticketstore.service.ArtistService;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists(){
        return ResponseEntity.ok(artistService.getAllArtist());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Integer id){
        return ResponseEntity.ok(artistService.getArtistById(id));
    }
    
    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> createArtist(@Valid @RequestBody ArtistDTO dto){
        return ResponseEntity.ok(artistService.createArtist(dto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@Valid @RequestBody ArtistDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(artistService.updateArtist(dto, id));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id){
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
