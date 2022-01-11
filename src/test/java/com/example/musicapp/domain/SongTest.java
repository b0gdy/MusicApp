package com.example.musicapp.domain;

public class SongTest {

    public static Song createSongTest(String name) {

        return Song.builder()
                .name(name)
                .artistId(1)
                .build();

    }

    public static Song createSongTest(Integer id) {

        return Song.builder()
                .id(id)
                .name("song1_test")
                .artistId(1)
                .build();

    }
    
}
