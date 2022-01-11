package com.example.musicapp.mapper;

import com.example.musicapp.domain.UserSong;
import com.example.musicapp.dto.UserSongDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserSongMapper {

//    @Mapping(target = "userId", source = "userSong.userId")
//    @Mapping(target = "songId", source = "userSong.songId")
    @Mapping(target = "name", source = "userSong.name")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "songName", source = "song.name")
    UserSongDto mapToDto(UserSong userSong);


    @Mapping(target = "name", source = "userSongDto.name")
//    @Mapping(target = "user.name", source = "userSongDto.userName")
//    @Mapping(target = "song.name", source = "userSongDto.songName")
    UserSong mapToEntity(UserSongDto userSongDto);

}
