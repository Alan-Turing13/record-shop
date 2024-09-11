package com.northcoders.record_shop.controller.update;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.record_shop.controller.Controller;
import com.northcoders.record_shop.dto.AlbumDetails;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TestPutAlbumController {

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
    @DisplayName("Put album")
    void putAlbumTest() throws Exception{

        Long id = 1L;
        AlbumDetails mockUpdateAlbumDetails = new AlbumDetails(
                "Journey Through The Secret Life Of Plants",
                1979,
                Genre.SOUNDTRACK,
                "Stevie Wonder",
                ""
                );

        this.mockMvcController.perform(MockMvcRequestBuilders.put(
                "http://localhost:8080/api/v1/records/{id}", id)
                .content(mapper.writeValueAsString(mockUpdateAlbumDetails))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
