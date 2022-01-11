package com.example.musicapp.Dto;

import com.example.musicapp.dto.AddressDto;

public class AddressDtoTest {

    public static AddressDto createAddressDtoTest(String name) {

        return AddressDto.builder()
                .name(name)
                .build();

    }
    
}
