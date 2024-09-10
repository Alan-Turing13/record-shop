package com.northcoders.record_shop.controller.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.record_shop.controller.Controller;
import com.northcoders.record_shop.dto.AlbumDetails;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestPostAlbumController {

    @Mock
    private AlbumService mockAlbumService;

    @InjectMocks
    private Controller controller;

    @Autowired
    private MockMvc mockMvcController;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("post an album")
    void postAlbumTest() throws Exception{

        Long id = 1L;
        Album mockAlbum = new Album(
                id,
                "The Real McCoy",
                1967,
                Genre.JAZZ,
                "McCoy Tyner",
                ""
        );
        AlbumDetails mockAlbumDetails = new AlbumDetails(
                        "The Real McCoy",
                        1967,
                        Genre.JAZZ,
                        "McCoy Tyner",
                        ""
                );

        when(mockAlbumService.postAlbum(mockAlbumDetails)).thenReturn(mockAlbum);

        this.mockMvcController.perform(MockMvcRequestBuilders.post(
                "http://localhost:8080/api/v1/records"
        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mockAlbum))
            ).andExpect(status().isCreated());

        verify(mockAlbumService, times(1)).postAlbum(mockAlbumDetails);
    }
}
