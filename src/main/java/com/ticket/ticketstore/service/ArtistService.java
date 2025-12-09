package com.ticket.ticketstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.ticketstore.dto.ArtistDTO;
import com.ticket.ticketstore.mapper.ArtistMapper;
import com.ticket.ticketstore.model.Artist;
import com.ticket.ticketstore.repository.ArtistRepository;

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
