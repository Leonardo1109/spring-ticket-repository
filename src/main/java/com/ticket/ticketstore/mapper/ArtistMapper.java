package com.ticket.ticketstore.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ticket.ticketstore.dto.ArtistDTO;
import com.ticket.ticketstore.model.Artist;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
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
