package com.example.musicapp.controller;

import com.example.musicapp.Dto.ArtistDtoTest;
import com.example.musicapp.Dto.ArtistMemberDtoTest;
import com.example.musicapp.Service.ArtistService;
import com.example.musicapp.dto.ArtistDto;
import com.example.musicapp.dto.ArtistMemberDto;
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

@WebMvcTest(controllers = ArtistController.class)
class ArtistControllerTest {

    @MockBean
    private ArtistService artistService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createArtistTest() throws Exception {

        //Arrange
        ArtistDto artistDto = ArtistDtoTest.createArtistDtoTest("artist1_test");

        when(artistService.create(any())).thenReturn(artistDto);

        //Act
        MvcResult result = mockMvc.perform(post("/artists")
                        .content(objectMapper.writeValueAsString(artistDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(artistDto));

    }

    @Test
    void getAllArtistsTest() throws Exception {

        //Arrange
        ArtistDto artistDto1 = ArtistDtoTest.createArtistDtoTest("artist1_test");
        ArtistDto artistDto2 = ArtistDtoTest.createArtistDtoTest("artist2_test");
        List<ArtistDto> artistDtos = new ArrayList<>();
        artistDtos.add(artistDto1);
        artistDtos.add(artistDto2);

        when(artistService.getAll()).thenReturn(artistDtos);

        //Act
        mockMvc.perform(get("/artists"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getArtistTest() throws Exception {

        //Arrange
        String name = "artist1_test";
        ArtistDto artistDto = ArtistDtoTest.createArtistDtoTest(name);

        when(artistService.get(name)).thenReturn(artistDto);

        //Act
        mockMvc.perform(get("/artists/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(artistDto.getName())));

    }

    @Test
    void getArtistTest_NotFound() throws Exception {

        //Arrange
        String name = "artist1_test";

        when(artistService.get(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/artists/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void getArtistMemberTest() throws Exception {

        //Arrange
        String name = "artist1_test";
        ArtistMemberDto artistMemberDto = ArtistMemberDtoTest.createArtistMemberDtoTest(name);

        when(artistService.getMember(name)).thenReturn(artistMemberDto);

        //Act
        mockMvc.perform(get("/artists/member/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getArtistMemberTest_NotFound() throws Exception {

        //Arrange
        String name = "artistMember1_test";

        when(artistService.getMember(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/artists/member/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteArtistTest() throws Exception {

        //Arrange
        String name = "artist1_test";

        artistService.delete(name);

        //Act
        mockMvc.perform(delete("/artists/" + name))
                .andExpect(status().isOk());

    }

    @Test
    void updateArtistTest() throws Exception {

        //Arrange
        String name1 = "artist1_test";
        String name2 = "artist2_test";

        artistService.update(name1, name2);

        //Act
        mockMvc.perform(put("/artists")
                        .param("name1", name1)
                        .param("name2", name2))
                .andExpect(status().isOk());

    }
    
}
