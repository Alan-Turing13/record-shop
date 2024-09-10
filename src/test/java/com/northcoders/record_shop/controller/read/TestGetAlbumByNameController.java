package com.northcoders.record_shop.controller.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.record_shop.controller.Controller;
import com.northcoders.record_shop.dto.AlbumNameDTO;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestGetAlbumByNameController {

    @Mock
    private AlbumService mockAlbumService;

    @InjectMocks
    private Controller controller;

    @Autowired
    private MockMvc mockMvcController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("get album by name")
    void getAlbumByNameTest() throws Exception{

        AlbumNameDTO mockAlbumNameDTO = new AlbumNameDTO("Piano Concertos Nos. 2 & 3");
        int releaseYear = 1990;

        Album mockAlbum = new Album(
                1L,
                mockAlbumNameDTO.name(),
                releaseYear,
                Genre.NEOCLASSICAL,
                "Sergei Prokofiev",
                ""
        );

        when(mockAlbumService.getAlbumByName(mockAlbumNameDTO.name())).thenReturn(mockAlbum);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/api/v1/records/album")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockAlbumNameDTO))
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.releaseYear")
                        .value(releaseYear)
                );
    }

}
