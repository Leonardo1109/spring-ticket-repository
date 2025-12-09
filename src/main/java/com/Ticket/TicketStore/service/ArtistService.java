package com.Ticket.TicketStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ticket.TicketStore.dto.ArtistDTO;
import com.Ticket.TicketStore.mapper.ArtistMapper;
import com.Ticket.TicketStore.model.Artist;
import com.Ticket.TicketStore.repository.ArtistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public List<ArtistDTO> getAllArtist(){
        return artistMapper.artistsToArtistDTOs(artistRepository.findAll());
    }

    public ArtistDTO getArtistById(Integer id){
        return artistMapper.artistToArtisDTO(artistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Artista no encontrado")));
    }

    public ArtistDTO createArtist(ArtistDTO dto){
        Artist artist = artistMapper.artistDTOToArtist(dto);

        artist = artistRepository.save(artist);

        return artistMapper.artistToArtisDTO(artist);
    }

    public ArtistDTO updateArtist(ArtistDTO dto, Integer id){
        Artist artist = artistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Artista no encontrado"));

        artist.setName(dto.getName());
        artist.setEmail(dto.getEmail());
        artist.setCountry(dto.getCountry());

        artist = artistRepository.save(artist);

        return artistMapper.artistToArtisDTO(artist);
    }

    public void deleteArtist(Integer id){
        if (!artistRepository.existsById(id)) {
            throw new RuntimeException("Artista no enceontrado");
        }
        artistRepository.deleteById(id);;
    }


}
