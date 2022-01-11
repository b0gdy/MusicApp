package com.example.musicapp.mapper;

import com.example.musicapp.domain.Artist;
import com.example.musicapp.domain.Song;
import com.example.musicapp.dto.ArtistMemberDto;
import com.example.musicapp.dto.SongDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArtistMemberMapper {

    @Mapping(target = "songDtos", source = "artist.songs", qualifiedByName = "songToSongDtos")
    ArtistMemberDto mapToDto(Artist artist);

    @Named(value = "songToSongDtos")
    default List<SongDto> songToSongDtos(List<Song> songs) {

//        return songs.stream().map(SongMapper.INSTANCE::mapToDto).collect(Collectors.toList());

        return songs.stream().map(s -> {

            SongDto songDto = new SongDto();
            songDto.setId(s.getId());
            songDto.setName(s.getName());
            songDto.setArtistId(s.getArtistId());
            songDto.setArtistName(s.getArtist().getName());

            return songDto;

        }).collect(Collectors.toList());

    }

    Artist mapToEntity(ArtistMemberDto artistMemberDto);
    
}
