package com.Ticket.TicketStore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.Ticket.TicketStore.dto.ArtistDTO;
import com.Ticket.TicketStore.model.Artist;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO artistToArtisDTO(Artist artist);

    List<ArtistDTO> artistsToArtistDTOs(List<Artist> artists);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    Artist artistDTOToArtist(ArtistDTO artistDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events", ignore = true)
    List<Artist> artistDTOsTOArtists(List<ArtistDTO> artistDTOs);
}
