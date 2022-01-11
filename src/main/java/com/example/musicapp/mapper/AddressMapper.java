package com.example.musicapp.mapper;

import com.example.musicapp.domain.Address;
import com.example.musicapp.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto mapToDto(Address address);

    Address mapToEntity(AddressDto addressDto);

}
