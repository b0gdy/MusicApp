package com.example.musicapp.mapper;

import com.example.musicapp.domain.Song;
import com.example.musicapp.domain.User;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.UserMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMemberMapper {

    @Mapping(target = "songDtos", source = "user.songs", qualifiedByName = "songToSongDtos")
    UserMemberDto mapToDto(User user);

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

    User mapToEntity(UserMemberDto userDto);

}
