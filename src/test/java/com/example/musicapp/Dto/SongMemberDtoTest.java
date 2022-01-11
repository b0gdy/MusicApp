package com.example.musicapp.Dto;

import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class SongMemberDtoTest {

    public static SongMemberDto createSongMemberDtoTest(String name) {

        UserDto userDto1 = UserDto.builder()
    //                .id(1)
                .name("user1_test")
                .address("address1_test")
                .build();

        UserDto userDto2 = UserDto.builder()
    //                .id(2)
                .name("user2_test")
                .address("address2_test")
                .build();

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto1);
        userDtos.add(userDto2);

        return SongMemberDto.builder()
                .name(name)
                .userDtos(userDtos)
                .build();

    }

}