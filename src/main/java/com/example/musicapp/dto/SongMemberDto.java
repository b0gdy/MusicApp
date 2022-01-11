package com.example.musicapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongMemberDto {

    private Integer id;

    private String name;

    private List<UserDto> userDtos = new ArrayList<>();

}
