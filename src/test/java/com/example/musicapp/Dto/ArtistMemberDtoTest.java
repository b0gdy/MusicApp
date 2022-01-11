package com.example.musicapp.Dto;

import com.example.musicapp.dto.ArtistMemberDto;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.UserMemberDto;

import java.util.ArrayList;
import java.util.List;

public class ArtistMemberDtoTest {

    public static ArtistMemberDto createArtistMemberDtoTest(String name) {

        ArtistMemberDto artistMemberDto = ArtistMemberDto.builder()
                .name(name)
                .build();

        SongDto songDto1 = SongDto.builder()
//                .id(1)
                .name("song1_test")
                .artistId(artistMemberDto.getId())
                .artistName(artistMemberDto.getName())
                .build();

        SongDto songDto2 = SongDto.builder()
//                .id(2)
                .name("song2_test")
                .artistId(artistMemberDto.getId())
                .artistName(songDto1.getName())
                .build();

        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        return artistMemberDto;

    }

}