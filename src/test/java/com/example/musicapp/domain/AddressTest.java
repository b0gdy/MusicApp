package com.example.musicapp.domain;

public class AddressTest {

    public static Address createAddressTest(String name) {

        return Address.builder()
                .name(name)
                .build();

    }

    public static Address createAddressTest(Integer id) {

        return Address.builder()
                .id(id)
                .name("address1_test")
                .build();

    }
    
}
