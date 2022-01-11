package com.example.musicapp.Dto;

import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.UserMemberDto;

import java.util.ArrayList;
import java.util.List;

public class UserMemberDtoTest {

    public static UserMemberDto createUserMemberDtoTest(String name) {

        SongDto songDto1 = SongDto.builder()
//                .id(1)
                .name("song1_test")
                .artistId(1)
                .artistName("artist1_test")
                .build();

        SongDto songDto2 = SongDto.builder()
//                .id(2)
                .name("song2_test")
                .artistId(2)
                .artistName("artist2_test")
                .build();

        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        return UserMemberDto.builder()
                .name(name)
                .songDtos(songDtos)
                .build();

    }

}
