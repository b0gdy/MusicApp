package com.example.musicapp.mapper;

import com.example.musicapp.domain.Song;
import com.example.musicapp.dto.SongDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface SongMapper {

//    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "artistName", source = "artist.name")
    SongDto mapToDto(Song song);

    Song mapToEntity(SongDto songDto);
    
}
