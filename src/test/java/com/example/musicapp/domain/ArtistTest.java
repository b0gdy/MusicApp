package com.example.musicapp.domain;

public class ArtistTest {

    public static Artist createArtistTest(String name) {

        return Artist.builder()
                .name(name)
                .build();

    }

    public static Artist createArtistTest(Integer id) {

        return Artist.builder()
                .id(id)
                .name("artist1_test")
                .build();

    }
    
}