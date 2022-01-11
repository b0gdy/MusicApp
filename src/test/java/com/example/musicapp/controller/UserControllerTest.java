package com.example.musicapp.controller;

import com.example.musicapp.Dto.UserDtoTest;
import com.example.musicapp.Dto.UserMemberDtoTest;
import com.example.musicapp.Service.UserService;
import com.example.musicapp.dto.UserDto;
import com.example.musicapp.dto.UserMemberDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserTest() throws Exception {

        //Arrange
        UserDto userDto = UserDtoTest.createUserDtoTest("user1_test");

        when(userService.create(any())).thenReturn(userDto);

        //Act
        MvcResult result = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(userDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(userDto));

    }

    @Test
    void getAllUsersTest() throws Exception {

        //Arrange
        UserDto userDto1 = UserDtoTest.createUserDtoTest("user1_test");
        UserDto userDto2 = UserDtoTest.createUserDtoTest("user2_test");
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto1);
        userDtos.add(userDto2);

        when(userService.getAll()).thenReturn(userDtos);

        //Act
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getUserTest() throws Exception {

        //Arrange
        String name = "user1_test";
        UserDto userDto = UserDtoTest.createUserDtoTest(name);

        when(userService.get(name)).thenReturn(userDto);

        //Act
        mockMvc.perform(get("/users/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(userDto.getName())));

    }

    @Test
    void getUserTest_NotFound() throws Exception {

        //Arrange
        String name = "user1_test";

        when(userService.get(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/users/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void getUserMemberTest() throws Exception {

        //Arrange
        String name = "user1_test";
        UserMemberDto userMemberDto = UserMemberDtoTest.createUserMemberDtoTest(name);

        when(userService.getMember(name)).thenReturn(userMemberDto);

        //Act
        mockMvc.perform(get("/users/member/" + name))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getUserMemberTest_NotFound() throws Exception {

        //Arrange
        String name = "userMember1_test";

        when(userService.getMember(name)).thenThrow(new MyNoContentException(name));

        //Act
        mockMvc.perform(get("/users/member/" + name))
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteUserTest() throws Exception {

        //Arrange
        String name = "user1_test";

        userService.delete(name);

        //Act
        mockMvc.perform(delete("/users/" + name))
                .andExpect(status().isOk());

    }

    @Test
    void updateUserTest() throws Exception {

        //Arrange
        String name1 = "user1_test";
        String name2 = "user2_test";

        userService.update(name1, name2);

        //Act
        mockMvc.perform(put("/users")
                        .param("name1", name1)
                        .param("name2", name2))
                .andExpect(status().isOk());

    }

}
