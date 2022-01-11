package com.example.musicapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSongDto {

    private Integer id;

    private String name;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer songId;

    private String userName;

    private String songName;

}
