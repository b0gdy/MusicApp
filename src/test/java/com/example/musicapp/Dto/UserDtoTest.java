package com.example.musicapp.Dto;

import com.example.musicapp.dto.UserDto;

public class UserDtoTest {

    public static UserDto createUserDtoTest(String name) {

        return UserDto.builder()
                .name(name)
                .address("address1_test")
                .build();

    }

}
