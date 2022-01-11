package com.example.musicapp.domain;

public class UserSongTest {

    public static UserSong createUserSongTest(Integer userId, Integer songId) {

        return UserSong.builder()
                .name(userId.toString() + "_" + songId.toString())
                .userId(userId)
                .songId(songId)
                .build();

    }

    public static UserSong createUserSongTest(Integer id) {

        return UserSong.builder()
                .id(id)
                .name("1_1")
                .userId(1)
                .songId(1)
                .build();

    }
    
}
