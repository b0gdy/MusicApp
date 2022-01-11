package com.example.musicapp.Dto;

import com.example.musicapp.dto.SongDto;

public class SongDtoTest {

    public static SongDto createSongDtoTest(String name) {

        return SongDto.builder()
                .name(name)
                .artistId(1)
                .artistName("artist1_test")
                .build();

    }
    
}
