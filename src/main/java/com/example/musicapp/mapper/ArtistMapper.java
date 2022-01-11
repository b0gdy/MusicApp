package com.example.musicapp.mapper;

import com.example.musicapp.domain.Artist;
import com.example.musicapp.dto.ArtistDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDto mapToDto(Artist artist);

    Artist mapToEntity(ArtistDto artistDto);

}
