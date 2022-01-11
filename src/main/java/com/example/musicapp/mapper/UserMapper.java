package com.example.musicapp.mapper;

import com.example.musicapp.domain.User;
import com.example.musicapp.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "address", source = "address.name")
    UserDto mapToDto(User user);

    @Mapping(target = "address.name", source = "userDto.address")
    User mapToEntity(UserDto userDto);

}
