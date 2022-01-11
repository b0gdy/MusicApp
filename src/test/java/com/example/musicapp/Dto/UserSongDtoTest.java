package com.example.musicapp.Dto;

import com.example.musicapp.dto.UserSongDto;

public class UserSongDtoTest {

    public static UserSongDto createUserSongDtoTest(Integer userId, Integer songId) {

        return UserSongDto.builder()
                .name(userId.toString() + "_" + songId.toString())
                .userId(userId)
                .songId(songId)
                .build();

    }
    
}
