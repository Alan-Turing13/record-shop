package com.northcoders.record_shop.controller.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.record_shop.controller.Controller;
import com.northcoders.record_shop.dto.ArtistNameDTO;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestGetAlbumsByArtistController {

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
    @DisplayName("get albums by artist")
    void getAlbumsByArtistTest() throws Exception{
        ArtistNameDTO mockArtistNameDTO = new ArtistNameDTO("Sergei Prokofiev");
        String name = "Piano Concertos Nos. 2 & 3";
        List<Album> mockAlbums = List.of(new Album(
                1L,
                name,
                1990,
                Genre.NEOCLASSICAL,
                mockArtistNameDTO.artistName()
        ));

        when(mockAlbumService.getAlbumsByArtist(mockArtistNameDTO.artistName()))
                .thenReturn(mockAlbums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/v1/records/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockArtistNameDTO))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].name")
                        .value(name)
                );
    }
}
