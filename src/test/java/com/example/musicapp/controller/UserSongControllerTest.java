package com.example.musicapp.controller;

import com.example.musicapp.Dto.UserSongDtoTest;
import com.example.musicapp.Service.UserSongService;
import com.example.musicapp.dto.UserSongDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserSongController.class)
class UserUserSongControllerTest {

    @MockBean
    private UserSongService userSongService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserSongTest() throws Exception {

        //Arrange
        UserSongDto userSongDto = UserSongDtoTest.createUserSongDtoTest(1, 1);

        when(userSongService.create(any())).thenReturn(userSongDto);

        //Act
        MvcResult result = mockMvc.perform(post("/users_songs")
                        .content(objectMapper.writeValueAsString(userSongDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(userSongDto));

    }

    @Test
    void getUserSongTest() throws Exception {

        //Arrange
        Integer id = 1;
        UserSongDto userSongDto = UserSongDtoTest.createUserSongDtoTest(1, 1);

        when(userSongService.get(1)).thenReturn(userSongDto);

        //Act
        mockMvc.perform(get("/users_songs/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(userSongDto.getName())));

    }

    @Test
    void getUserSongTest_NotFound() throws Exception {

        //Arrange
        Integer id = 1;

        when(userSongService.get(id)).thenThrow(new MyNoContentException(id.toString()));

        //Act
        mockMvc.perform(get("/users_songs/" + id))
                .andExpect(status().isNoContent());

    }

    @Test
    void  getUserSongByUserNameTest() throws Exception {

        //Arrange
        String userName = "user1_test";
        UserSongDto userSongDto1 = UserSongDtoTest.createUserSongDtoTest(1, 1);
        UserSongDto userSongDto2 = UserSongDtoTest.createUserSongDtoTest(1, 2);
        List<UserSongDto> userSongDtos = new ArrayList<>();
        userSongDtos.add(userSongDto1);
        userSongDtos.add(userSongDto2);

        when(userSongService.getFilteredByUserName(userName)).thenReturn(userSongDtos);

        //Act
        mockMvc.perform(get("/users_songs/filterByUserName")
                        .param("userName", userName))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void  getUserSongByUserNameTest_NotFound() throws Exception {

        //Arrange
        String userName = "user1_test";

        when(userSongService.getFilteredByUserName(userName)).thenThrow(new MyNoContentException(userName));

        //Act
        mockMvc.perform(get("/users_songs/filterByUserName")
                        .param("userName", userName))
                .andExpect(status().isNoContent());

    }

    @Test
    void  getUserSongBySongNameTest() throws Exception {

        //Arrange
        String songName = "song1_test";
        UserSongDto userSongDto1 = UserSongDtoTest.createUserSongDtoTest(1, 1);
        UserSongDto userSongDto2 = UserSongDtoTest.createUserSongDtoTest(2, 1);
        List<UserSongDto> userSongDtos = new ArrayList<>();
        userSongDtos.add(userSongDto1);
        userSongDtos.add(userSongDto2);

        when(userSongService.getFilteredBySongName(songName)).thenReturn(userSongDtos);

        //Act
        mockMvc.perform(get("/users_songs/filterBySongName")
                        .param("songName", songName))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void  getUserSongBySongNameTest_NotFound() throws Exception {

        //Arrange
        String songName = "song1_test";

        when(userSongService.getFilteredBySongName(songName)).thenThrow(new MyNoContentException(songName));

        //Act
        mockMvc.perform(get("/users_songs/filterBySongName")
                        .param("songName", songName))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteUserSongTest() throws Exception {

        //Arrange
        Integer id = 1;

        userSongService.delete(id);

        //Act
        mockMvc.perform(delete("/users_songs/" + id))
                .andExpect(status().isOk());

    }
    
}
