package com.example.musicapp.domain;

public class UserTest {

    public static User createUserTest(String name) {

        return User.builder()
                .name(name)
                .address(Address.builder()
                        .name("address1_test")
                        .build())
                .build();

    }

    public static User createUserTest(Integer id) {

        return User.builder()
                .id(id)
                .name("user1_test")
                .address(Address.builder()
                        .name("address1_test")
                        .build())
                .build();

    }

}
