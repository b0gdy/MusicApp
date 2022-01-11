package com.example.musicapp.controller;

import com.example.musicapp.Dto.SongDtoTest;
import com.example.musicapp.Dto.SongMemberDtoTest;
import com.example.musicapp.Service.SongService;
import com.example.musicapp.dto.SongDto;
import com.example.musicapp.dto.SongMemberDto;
import com.example.musicapp.exception.MyNoContentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SongController.class)
class SongControllerTest {

    @MockBean
    private SongService songService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createSongTest() throws Exception {

        //Arrange
        SongDto songDto = SongDtoTest.createSongDtoTest("song1_test");

        when(songService.create(any())).thenReturn(songDto);

        //Act
        MvcResult result = mockMvc.perform(post("/songs")
                        .content(objectMapper.writeValueAsString(songDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(songDto));

    }

    @Test
    void getAllSongsTest() throws Exception {

        //Arrange
        SongDto songDto1 = SongDtoTest.createSongDtoTest("song1_test");
        SongDto songDto2 = SongDtoTest.createSongDtoTest("song2_test");
        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        when(songService.getAll()).thenReturn(songDtos);

        //Act
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getSongTest() throws Exception {

        //Arrange
        String name = "song1_test";
        SongDto songDto = SongDtoTest.createSongDtoTest(name);

        when(songService.get(name)).thenReturn(songDto);

        //Act
        mockMvc.perform(get("/songs/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(songDto.getName())));

    }

    @Test
    void getSongTest_NotFound() throws Exception {

        //Arrange
        String name = "song1_test";

        when(songService.get(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/songs/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void getSongMemberTest() throws Exception {

        //Arrange
        String name = "song1_test";
        SongMemberDto songMemberDto = SongMemberDtoTest.createSongMemberDtoTest(name);

        when(songService.getMember(name)).thenReturn(songMemberDto);

        //Act
        mockMvc.perform(get("/songs/member/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getSongMemberTest_NotFound() throws Exception {

        //Arrange
        String name = "songMember1_test";

        when(songService.getMember(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/songs/member/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void getUserSongByArtistNameTest() throws Exception {

        //Arrange
        String artistName = "artist1_test";
        SongDto songDto1 = SongDtoTest.createSongDtoTest("song1_test");
        SongDto songDto2 = SongDtoTest.createSongDtoTest("song2_test");
        List<SongDto> songDtos = new ArrayList<>();
        songDtos.add(songDto1);
        songDtos.add(songDto2);

        when(songService.getFilteredByArtistName(artistName)).thenReturn(songDtos);

        //Act
        mockMvc.perform(get("/songs/filterByArtistName")
                        .param("artistName", artistName))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getUserSongByArtistNameTest_NotFound() throws Exception {

        //Arrange
        String artistName = "artist1_test";

        when(songService.getFilteredByArtistName(artistName)).thenThrow(new MyNoContentException(artistName));

        //Act
        mockMvc.perform(get("/songs/filterByArtistName")
                        .param("artistName", artistName))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteSongTest() throws Exception {

        //Arrange
        String name = "song1_test";

        songService.delete(name);

        //Act
        mockMvc.perform(delete("/songs/" + name))
                .andExpect(status().isOk());

    }

    @Test
    void updateSongTest() throws Exception {

        //Arrange
        String name1 = "song1_test";
        String name2 = "song2_test";

        songService.update(name1, name2);

        //Act
        mockMvc.perform(put("/songs")
                        .param("name1", name1)
                        .param("name2", name2))
                .andExpect(status().isOk());

    }

}
