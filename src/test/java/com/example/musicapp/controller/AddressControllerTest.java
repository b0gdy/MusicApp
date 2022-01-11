package com.example.musicapp.controller;

import com.example.musicapp.Service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @MockBean
    private AddressService addressService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deleteAddressTest() throws Exception {

        //Arrange
        Integer id = 1;

        addressService.delete(id);

        //Act
        mockMvc.perform(delete("/addresses/" + id))
                .andExpect(status().isOk());

    }

    @Test
    void updateAddressTest() throws Exception {

        //Arrange
        Integer id = 1;
        String name = "address1_test";

        addressService.update(id, name);

        //Act
        mockMvc.perform(put("/addresses")
                        .param("id", String.valueOf(id))
                        .param("name", name))
                .andExpect(status().isOk());

    }

}