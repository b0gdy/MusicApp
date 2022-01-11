package com.example.musicapp.Dto;

import com.example.musicapp.dto.ArtistDto;

public class ArtistDtoTest {

    public static ArtistDto createArtistDtoTest(String name) {

        return ArtistDto.builder()
                .name(name)
                .build();

    }

}