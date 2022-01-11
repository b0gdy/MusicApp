package com.example.musicapp.mapper;

import com.example.musicapp.domain.Song;
import com.example.musicapp.domain.User;
import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SongMemberMapper {

    @Mapping(target = "userDtos", source = "song.users", qualifiedByName = "userToUserDtos")
    SongMemberDto mapToDto(Song song);

    @Named(value = "userToUserDtos")
    default List<UserDto> userToUserDtos(List<User> users) {

//        return users.stream().map(UserMapper.INSTANCE::mapToDto).collect(Collectors.toList());

        return users.stream().map(u -> {

            UserDto userDto = new UserDto();
            userDto.setId(u.getId());
            userDto.setName(u.getName());
            userDto.setAddress(u.getAddress().getName());

            return userDto;

        }).collect(Collectors.toList());

    }

    Song mapToEntity(SongMemberDto songDto);

}
